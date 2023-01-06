package com.example.clickup.service;


import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.MemberDTO;
import com.example.clickup.payload.WorkspaceDTO;

import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, Users user);

    ApiResponse editWorkspace(WorkspaceDTO workspaceDTO);

    ApiResponse deleteWorkspace(Long id);
    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);


    ApiResponse userget(Long workspace_id, UUID user_id);


}
