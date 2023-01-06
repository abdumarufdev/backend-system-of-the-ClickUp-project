package com.example.clickup.controller;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDTO;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/space")
public class SpaceController {
    @Autowired
    SpaceService spaceService;
    @PostMapping
    public HttpEntity<?> addSpace(@RequestBody SpaceDTO spaceDTO,@CurrentUser Users users){
        ApiResponse apiResponse=spaceService.addSpaceService(spaceDTO,users);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
