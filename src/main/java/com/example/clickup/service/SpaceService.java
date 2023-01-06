package com.example.clickup.service;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDTO;

public interface SpaceService {
    ApiResponse addSpaceService(SpaceDTO spaceDTO, Users users);
}
