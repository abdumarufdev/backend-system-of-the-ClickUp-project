package com.example.clickup.service;

import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.CategoryDTO;

public interface CategoryService {
    ApiResponse addProjectService(CategoryDTO categoryDTO);
}
