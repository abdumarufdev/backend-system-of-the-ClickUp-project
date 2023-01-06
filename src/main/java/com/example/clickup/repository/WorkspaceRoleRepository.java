package com.example.clickup.repository;


import com.example.clickup.entity.WorkspacePermission;
import com.example.clickup.entity.WorkspaceRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
}
