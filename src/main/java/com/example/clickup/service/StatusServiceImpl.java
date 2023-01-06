package com.example.clickup.service;

import com.example.clickup.entity.*;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.StatusDTO;
import com.example.clickup.repository.CategoryRepazitori;
import com.example.clickup.repository.ProjectRepazitori;
import com.example.clickup.repository.SpaceRepazitori;
import com.example.clickup.repository.StatusRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    SpaceRepazitori spaceRepazitori;
    @Autowired
    CategoryRepazitori categoryRepazitori;
    @Autowired
    ProjectRepazitori projectRepazitori;
    @Autowired
    StatusRepazitori statusRepazitori;
    @Override
    public ApiResponse addstatusService(StatusDTO statusDTO, Users users) {
        Optional<Space> spaceOptional = spaceRepazitori.findById(statusDTO.getSpaceid());
        if (!spaceOptional.isPresent()) return new ApiResponse("Bunday Space mavjud emas!",false);
        Optional<Category> categoryOptional = categoryRepazitori.findById(statusDTO.getCategoryid());
        if (!categoryOptional.isPresent()) return new ApiResponse("Bunday Category mavjud emas!",false);
        Optional<Project> projectOptional = projectRepazitori.findById(statusDTO.getProjectid());
        if (!projectOptional.isPresent()) return new ApiResponse("Bunday Project mavjud emas!",false);
        statusRepazitori.save(new Status(
                statusDTO.getName(),spaceOptional.get(),projectOptional.get(),categoryOptional.get(),statusDTO.getColor(),statusDTO.getStatusType()
        ));
        return new ApiResponse("Successfuly add status",true);
    }
}
