package com.example.clickup.service;

import com.example.clickup.entity.Project;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.ProjectDTO;

public interface ProjectService {
    ApiResponse addProjectService(ProjectDTO projectDTO);
}
