package com.samhwan.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.response.admin.DeleteUserResponseDto;
import com.samhwan.dashboard.dto.response.admin.GetAdminUserListResponseDto;
import com.samhwan.dashboard.service.AdminService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("users")
    public ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList() {
        return adminService.getAdminUserList();
    }
    @DeleteMapping("users/{userId}")
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(
        @PathVariable("userId") String userId
    ) {
        
        return adminService.deleteUser(userId);
    }
    @PutMapping("users/{userId}")
    public String updateUser(@RequestBody String entity) {
        
        return entity;
    }


}
