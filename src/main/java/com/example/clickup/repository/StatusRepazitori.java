package com.example.clickup.repository;

import com.example.clickup.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusRepazitori extends JpaRepository<Status, UUID> {
}
