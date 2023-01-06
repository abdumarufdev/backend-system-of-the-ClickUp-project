package com.example.clickup.repository;


import com.example.clickup.entity.WorkspacePermission;
import com.example.clickup.entity.enums.WorkspacePermissionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);

    @Transactional
    @Modifying
    void deleteByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);
}
