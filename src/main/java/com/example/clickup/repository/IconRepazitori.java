package com.example.clickup.repository;

import com.example.clickup.entity.Icon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IconRepazitori extends JpaRepository<Icon, UUID> {
}
