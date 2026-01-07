package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.admin.DeleteUserResponseDto;
import com.samhwan.dashboard.dto.response.admin.GetAdminUserListResponseDto;
import com.samhwan.dashboard.dto.response.admin.UpdateUserResponseDto;

public interface AdminService {

    ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList();
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId);
    ResponseEntity<? super UpdateUserResponseDto> updateUser(String userId);    
    
}