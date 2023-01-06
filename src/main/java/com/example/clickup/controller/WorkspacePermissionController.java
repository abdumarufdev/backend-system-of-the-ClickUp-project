package com.example.clickup.controller;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.PermissionDTO;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.WorkspacePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class WorkspacePermissionController {
    @Autowired
    WorkspacePermissionService workspacePermissionService;
    @PostMapping("/addorremovepermission")
    public HttpEntity<?> addOrRemove(@CurrentUser Users users, @RequestBody PermissionDTO permissionDTO){
        ApiResponse apiResponse=workspacePermissionService.addorremoveservice(permissionDTO,users);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
