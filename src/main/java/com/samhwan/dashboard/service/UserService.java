package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.user.GetAdminUserListResponseDto;
import com.samhwan.dashboard.dto.response.user.GetSignInUserResponseDto;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);

    ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList();
    
}