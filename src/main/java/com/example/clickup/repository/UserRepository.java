package com.example.clickup.repository;

import com.example.clickup.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
}
