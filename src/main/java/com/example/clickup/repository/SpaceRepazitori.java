package com.example.clickup.repository;

import com.example.clickup.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpaceRepazitori extends JpaRepository<Space, UUID> {
    Optional<Space> findByWorkspaceIdAndName(Long workspace_id, String name);
}
