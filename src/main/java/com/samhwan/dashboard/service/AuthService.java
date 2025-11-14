package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.auth.SignInRequestDto;

public interface AuthService {
    ResponseEntity<? super SignInRequestDto> signIn(SignInRequestDto dto);
}