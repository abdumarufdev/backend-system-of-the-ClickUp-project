package com.example.clickup.service;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.PermissionDTO;

public interface WorkspacePermissionService {
    ApiResponse addorremoveservice(PermissionDTO permissionDTO, Users users);
}
