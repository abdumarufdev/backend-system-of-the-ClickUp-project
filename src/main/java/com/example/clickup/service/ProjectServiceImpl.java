package com.example.clickup.service;

import com.example.clickup.entity.Project;
import com.example.clickup.entity.Space;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.ProjectDTO;
import com.example.clickup.repository.ProjectRepazitori;
import com.example.clickup.repository.SpaceRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepazitori projectRepazitori;
    @Autowired
    SpaceRepazitori spaceRepazitori;
    @Override
    public ApiResponse addProjectService(ProjectDTO projectDTO) {
        Optional<Space> spaceOptional = spaceRepazitori.findById(projectDTO.getSpaceid());
        if (!spaceOptional.isPresent()) return new ApiResponse("Bunday Space mavjud emas!",false);
        boolean exists = projectRepazitori.existsBySpaceIdAndName(projectDTO.getSpaceid(), projectDTO.getName());
        if (exists) return new ApiResponse("Bunday project mavjud!",false);
        projectRepazitori.save(new Project(
                projectDTO.getName(),spaceOptional.get(),projectDTO.getAccesType(),projectDTO.getArchived(),projectDTO.getColor()
        ));
        return new ApiResponse("Successfuly ADD project",true);
    }
}
