package com.example.clickup.repository;

import com.example.clickup.entity.Category;
import com.example.clickup.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepazitori extends JpaRepository<Category, UUID> {
    boolean existsByProjectIdAndName(UUID project_id, String name);
}
