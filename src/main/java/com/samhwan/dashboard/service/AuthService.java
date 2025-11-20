package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.auth.SignInRequestDto;
import com.samhwan.dashboard.dto.request.auth.SignUpRequestDto;
import com.samhwan.dashboard.dto.response.auth.SignUpResponseDto;
import com.samhwan.dashboard.dto.response.auth.SignInResponseDto;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super SignInResponseDto> (SignInRequestDto dto);
}