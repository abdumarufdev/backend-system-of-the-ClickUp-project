package com.example.clickup.controller;

import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.StatusDTO;
import com.example.clickup.payload.TaskDTO;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.StatusService;
import com.example.clickup.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping
    public HttpEntity<?> addtask(@ModelAttribute TaskDTO taskDTO, @CurrentUser Users users) throws IOException {
        ApiResponse apiResponse=taskService.addtaskService(taskDTO,users);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
