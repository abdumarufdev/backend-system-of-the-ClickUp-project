package com.example.clickup.service;

import com.example.clickup.entity.Users;
import com.example.clickup.entity.WorkspacePermission;
import com.example.clickup.entity.WorkspaceRole;
import com.example.clickup.entity.enums.AddType;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.PermissionDTO;
import com.example.clickup.repository.WorkspacePermissionRepository;
import com.example.clickup.repository.WorkspaceRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkspacePermissionServiceImpl implements WorkspacePermissionService{
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Override
    public ApiResponse addorremoveservice(PermissionDTO permissionDTO, Users users) {
        Optional<WorkspacePermission> byWorkspaceRoleIdAndPermission_name = workspacePermissionRepository.findByWorkspaceRoleIdAndPermission(permissionDTO.getId(),permissionDTO.getWorkspacePermission());
        if (permissionDTO.getAddType().equals(AddType.ADD)){
            if (byWorkspaceRoleIdAndPermission_name.isPresent()) return new ApiResponse("Bu Roleda Bundanu huquq mavjud!",false);
            Optional<WorkspaceRole> byId = workspaceRoleRepository.findById(permissionDTO.getId());
            workspacePermissionRepository.save(new WorkspacePermission(
               byId.get(),permissionDTO.getWorkspacePermission()
            ));
            return new ApiResponse("Successfuly ADD",true);
        }
        else if(permissionDTO.getAddType().equals(AddType.REMOVE)){
            if (!byWorkspaceRoleIdAndPermission_name.isPresent()) return new ApiResponse("Bu Roleda Bundanu huquq mavjud emas!",false);
            workspacePermissionRepository.deleteByWorkspaceRoleIdAndPermission(permissionDTO.getId(),permissionDTO.getWorkspacePermission());
            return new ApiResponse("Successfuly Remove",true);
        }
      return new ApiResponse("i",false);
    }
}
