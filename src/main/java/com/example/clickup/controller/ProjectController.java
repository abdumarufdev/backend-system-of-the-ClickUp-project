package com.example.clickup.controller;

import com.example.clickup.entity.Project;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.ProjectDTO;
import com.example.clickup.repository.ProjectRepazitori;
import com.example.clickup.repository.SpaceRepazitori;
import com.example.clickup.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping
    public HttpEntity<?> addproject(@RequestBody ProjectDTO projectDTO){
        ApiResponse apiResponse=projectService.addProjectService(projectDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
