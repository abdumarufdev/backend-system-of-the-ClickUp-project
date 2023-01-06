package com.example.clickup.service;


import com.example.clickup.entity.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.TaskDTO;

import java.io.IOException;

public interface TaskService {

    ApiResponse addtaskService(TaskDTO taskDTO, Users users) throws IOException;
}
