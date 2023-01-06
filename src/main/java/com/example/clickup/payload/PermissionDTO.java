package com.example.clickup.payload;

import com.example.clickup.entity.WorkspacePermission;
import com.example.clickup.entity.enums.AddType;
import com.example.clickup.entity.enums.WorkspacePermissionName;
import lombok.Data;

import java.util.UUID;

@Data
public class PermissionDTO {
    private UUID id;
    private WorkspacePermissionName workspacePermission;
    private AddType addType;
}
