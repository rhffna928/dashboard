package com.samhwan.dashboard.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.request.admin.UpdateUserRequestDto;
import com.samhwan.dashboard.dto.response.admin.DeleteUserResponseDto;
import com.samhwan.dashboard.dto.response.admin.GetAdminUserListResponseDto;
import com.samhwan.dashboard.dto.response.admin.UpdateUserResponseDto;
import com.samhwan.dashboard.service.AdminService;

import jakarta.validation.Valid;
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
    public ResponseEntity<? super UpdateUserResponseDto> updateUser(
        Principal principal,
        @PathVariable("userId") String targetUserId,
        @RequestBody @Valid UpdateUserRequestDto requestBody) {
            
        String currentUserId = principal.getName();
        
        ResponseEntity<? super UpdateUserResponseDto> request = adminService.updateUser(currentUserId,targetUserId, requestBody);
        return request;
    }
    // @PatchMapping("users/{userId}/password-reset")
    // public ResponseEntity<? super ResetPasswordResponseDto> resetPassword(
    //     Principal principal,
    //     @PathVariable("userId") String targetUserId
    // ) {
    //     String currentUserId = principal.getName();
    //     return adminService.resetPassword(currentUserId, targetUserId);
    // }


}
