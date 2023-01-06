package com.example.clickup.config;

import com.example.clickup.entity.Users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class CreateByUser implements AuditorAware<UUID>{
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            Users users = (Users) authentication.getPrincipal();
            return Optional.of(users.getId());
        }
        return Optional.empty();
    }
}
