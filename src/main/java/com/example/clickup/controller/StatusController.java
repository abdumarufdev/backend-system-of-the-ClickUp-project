package com.example.clickup.controller;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDTO;
import com.example.clickup.payload.StatusDTO;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping
    public HttpEntity<?> addstatus(@RequestBody StatusDTO statusDTO, @CurrentUser Users users){
        ApiResponse apiResponse=statusService.addstatusService(statusDTO,users);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
