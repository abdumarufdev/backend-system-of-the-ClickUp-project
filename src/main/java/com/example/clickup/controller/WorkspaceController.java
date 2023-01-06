package com.example.clickup.controller;


import com.example.clickup.entity.Users;
import com.example.clickup.entity.Workspace;
import com.example.clickup.entity.WorkspaceUser;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.MemberDTO;
import com.example.clickup.payload.WorkspaceDTO;
import com.example.clickup.repository.WorkspaceUserRepository;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser Users user) {
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {
        ApiResponse apiResponse = workspaceService.editWorkspace(workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/AddOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @PutMapping("/emailtasdiqlash")
    public HttpEntity<?> Faolashtirish(@RequestParam Long workspace_id,@RequestParam UUID user_id){
        ApiResponse apiResponse=workspaceService.userget(workspace_id,user_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping("/menber")
    public  HttpEntity<?> ishchilar(@CurrentUser Users user){
        List<Users> usersList=new ArrayList<>();
        for (WorkspaceUser workspaceUser : workspaceUserRepository.findAll()) {
            if (workspaceUser.getWorkspace().getOwner().getId().equals(user.getId())){
                usersList.add(workspaceUser.getUser());
            }
        }
        return ResponseEntity.ok("malumotlar"+usersList);
    }
    @GetMapping("/myworkspace")
    public  HttpEntity<?> workspake(@CurrentUser Users user){
        List<Workspace> usersList=new ArrayList<>();
        for (WorkspaceUser workspaceUser : workspaceUserRepository.findAll()) {
            if (workspaceUser.getWorkspace().getOwner().getId().equals(user.getId())){
                usersList.add(workspaceUser.getWorkspace());
            }
        }
        return ResponseEntity.ok("malumotlar"+usersList);
    }
}
