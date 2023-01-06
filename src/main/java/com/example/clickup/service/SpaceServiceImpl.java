package com.example.clickup.service;

import com.example.clickup.entity.Space;
import com.example.clickup.entity.Users;
import com.example.clickup.entity.Workspace;
import com.example.clickup.entity.WorkspaceUser;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDTO;
import com.example.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaceServiceImpl implements SpaceService{
    @Autowired
    SpaceRepazitori spaceRepazitori;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    IconRepazitori iconRepazitori;
    @Override
    public ApiResponse addSpaceService(SpaceDTO spaceDTO, Users users) {
        Optional<Workspace> byId = workspaceRepository.findById(spaceDTO.getWorkspaceid());
        if (!byId.isPresent()) return new ApiResponse("Bunday Workspace mavjud emas!",false);
        Optional<WorkspaceUser> workspaceUserOptional = workspaceUserRepository.findByWorkspaceIdAndUserId(spaceDTO.getWorkspaceid(), users.getId());
        if (!workspaceUserOptional.isPresent()) return new ApiResponse("Bu Workspaceda bunday xodim mavjud emas!",false);
        Optional<Space> spaceOptional = spaceRepazitori.findByWorkspaceIdAndName(spaceDTO.getWorkspaceid(), spaceDTO.getName());
        if (spaceOptional.isPresent())  return new ApiResponse("Bu Workspaceda bunday Space mavjud!",false);
        spaceRepazitori.save(new Space(
           spaceDTO.getName(),spaceDTO.getColor(),byId.get(),null,null,users,spaceDTO.getAccessType()
        ));
        return new ApiResponse("Successfuly ADD Space",true);
    }
}
