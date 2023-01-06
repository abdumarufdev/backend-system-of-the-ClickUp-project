package com.example.clickup.service;

import com.example.clickup.entity.Category;
import com.example.clickup.entity.Project;
import com.example.clickup.entity.Space;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.CategoryDTO;
import com.example.clickup.repository.CategoryRepazitori;
import com.example.clickup.repository.ProjectRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    ProjectRepazitori projectRepazitori;
    @Autowired
    CategoryRepazitori categoryRepazitori;
    @Override
    public ApiResponse addProjectService(CategoryDTO categoryDTO) {
        Optional<Project> projectOptional = projectRepazitori.findById(categoryDTO.getProjectid());
        if (!projectOptional.isPresent()) return new ApiResponse("Bunday Project mavjud emas!",false);
        boolean exists = categoryRepazitori.existsByProjectIdAndName(categoryDTO.getProjectid(),categoryDTO.getName());
        if (exists) return new ApiResponse("Bunday Category mavjud!",false);
        categoryRepazitori.save(new Category(
                categoryDTO.getName(),projectOptional.get(),categoryDTO.getAccesType(),categoryDTO.getArchived(),categoryDTO.getColor()
        ));
        return new ApiResponse("Successfuly ADD category",true);
    }
}
