package com.example.clickup.controller;

import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.CategoryDTO;
import com.example.clickup.payload.ProjectDTO;
import com.example.clickup.service.CategoryService;
import com.example.clickup.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public HttpEntity<?> addcategory(@RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse=categoryService.addProjectService(categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
