package com.samhwan.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.response.user.GetAdminUserListResponseDto;
import com.samhwan.dashboard.dto.response.user.GetSignInUserResponseDto;
import com.samhwan.dashboard.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("user")
    public ResponseEntity<? super GetSignInUserResponseDto>getSignInUser(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList() {
        return userService.getAdminUserList();
    }
    

}
