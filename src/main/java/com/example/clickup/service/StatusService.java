package com.example.clickup.service;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.StatusDTO;

public interface StatusService {
    ApiResponse addstatusService(StatusDTO statusDTO, Users users);
}
