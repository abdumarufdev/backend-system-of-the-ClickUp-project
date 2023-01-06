package com.example.clickup.repository;

import com.example.clickup.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProjectRepazitori  extends JpaRepository<Project,UUID>{
    boolean existsBySpaceIdAndName(UUID space_id, String name);
}
