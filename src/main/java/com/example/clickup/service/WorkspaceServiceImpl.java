package com.example.clickup.service;


import com.example.clickup.entity.*;
import com.example.clickup.entity.enums.AddType;
import com.example.clickup.entity.enums.WorkspacePermissionName;
import com.example.clickup.entity.enums.WorkspaceRoleName;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.MemberDTO;
import com.example.clickup.payload.WorkspaceDTO;
import com.example.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, Users user) {
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName())) return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);


        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));

        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_ADMIN.name(),
                null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_MEMBER.name(),
                null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_GUEST.name(),
                null));



        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();
        for (WorkspacePermissionName permissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    permissionName
            );
            workspacePermissionList.add(workspacePermission);
            if (permissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissionList.add(new WorkspacePermission(
                        adminRole,
                        permissionName));
            }
            if (permissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissionList.add(new WorkspacePermission(
                        memberRole,
                        permissionName));
            }
            if (permissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissionList.add(new WorkspacePermission(
                        guestRole,
                        permissionName));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);


        workspaceUserRepository.save(new WorkspaceUser(
              workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
                ));

        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDTO workspaceDTO) {
        return null;
    }


    @Override
    public ApiResponse deleteWorkspace(Long id) {
        return null;
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            Optional<WorkspaceUser> byWorkspaceIdAndUserId = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId());
            if (byWorkspaceIdAndUserId.isPresent()) return new ApiResponse("bunday ishchi mavjud",false);
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);
            Optional<Users> byId = userRepository.findById(memberDTO.getId());
            tasdiqlash(byId.get().getEmail(),byId.get().getId(),id);

        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("SUCCESSFULLY", true);
    }
    public boolean tasdiqlash(String userEmail,UUID user_id , Long workspace_id){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom("Workspace@gamil.com");
            simpleMailMessage.setTo(userEmail);
            simpleMailMessage.setSubject("Emailni tasdiqlash!");
            simpleMailMessage.setText("<a href='http://localhost:8080/api/workspace/emailtasdiqlash?workspace_id="+workspace_id+"&user_id="+user_id+"'>hisobni faolashtirish</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }
    public ApiResponse userget(Long workspace_id,UUID user_id ){
        for (WorkspaceUser workspaceUser : workspaceUserRepository.findAll()) {
            if (workspaceUser.getWorkspace().getId().equals(workspace_id) && workspaceUser.getUser().getId().equals(user_id)){
                workspaceUser.setDateJoined( new Timestamp(System.currentTimeMillis()));
                workspaceUserRepository.save(workspaceUser);
                return new ApiResponse("Qoshildi!",true);
            }
        }
         return new ApiResponse("Qo'shilmadi",false);
    }

//    @Override
//    public List<Users> xodimmalumotlari(Users user) {
//        List<Users> usersList=new ArrayList<>();
//        for (WorkspaceUser workspaceUser : workspaceUserRepository.findAll()) {
//            if (workspaceUser.getWorkspace().getOwner().getId().equals(user.getId())){
//                usersList.add(workspaceUser.getUser());
//            }
//       }
//        return usersList;
//    }
}
