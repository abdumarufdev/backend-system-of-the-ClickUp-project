package com.example.clickup.service;

import com.example.clickup.entity.*;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.TaskDTO;
import com.example.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    CategoryRepazitori categoryRepazitori;
    @Autowired
    StatusRepazitori statusRepazitori;
    @Autowired
    TaskRepazitori taskRepazitori;
    @Autowired
    AttachmentRepository attachmentRepository;
    private String path="C:\\Users\\lenovo\\IdeaProjects\\ClickUp\\Avatar";
    @Override
    public ApiResponse addtaskService(TaskDTO taskDTO, Users users) throws IOException {
        Optional<Category> categoryOptional = categoryRepazitori.findById(taskDTO.getCategoryid());
        if (!categoryOptional.isPresent()) return new ApiResponse("Bunday Category mavjud emas!",false);
        Optional<Status> statusOptional = statusRepazitori.findById(taskDTO.getStatusid());
        if (!statusOptional.isPresent()) return new ApiResponse("Bunday Status mavjud emas!",false);
        Attachment fayl=null;
        if (!taskDTO.getAvatar().isEmpty()) {
            fayl=new Attachment();
            fayl.setOriginalName(taskDTO.getAvatar().getOriginalFilename());
            fayl.setContentType(taskDTO.getAvatar().getContentType());
            fayl.setSize(taskDTO.getAvatar().getSize());
            String newname = taskDTO.getAvatar().getOriginalFilename();
            String[] split = newname.split("\\.");
            String s = UUID.randomUUID().toString() + "." + split[split.length - 1];
            fayl.setPath(s);
            Path paths = Paths.get(path + s);
            Files.copy(taskDTO.getAvatar().getInputStream(), paths);
            attachmentRepository.save(fayl);
        }
        taskRepazitori.save(new Task(
                taskDTO.getName(),taskDTO.getDescription(),statusOptional.get(),categoryOptional.get(),null,taskDTO.getSubtask(),fayl,taskDTO.getStartData(),taskDTO.getStartTime(),taskDTO.getDueData(),taskDTO.getDueTime(),null,null
        ));
        return new ApiResponse("Successfuly add Task",true);
    }
}
