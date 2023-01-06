package com.example.clickup.repository;

import com.example.clickup.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepazitori extends JpaRepository<Task, UUID> {
}
