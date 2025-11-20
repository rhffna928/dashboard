package com.samhwan.dashboard.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.request.auth.SignInRequestDto;
import com.samhwan.dashboard.dto.request.auth.SignUpRequestDto;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.auth.SignInResponseDto;
import com.samhwan.dashboard.dto.response.auth.SignUpResponseDto;

import com.samhwan.dashboard.entity.User2;
import com.samhwan.dashboard.provider.JwtProvider;
import com.samhwan.dashboard.repository.User2Repository;
import com.samhwan.dashboard.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final User2Repository user2Repository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
            
        try{
            String userId = dto.getUserId();
            boolean exexistsById = user2Repository.existsByUserId(userId);
            if(exexistsById) return SignUpResponseDto.duplicateId();

            String password = dto.getUserPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            User2 user2 = new User2(dto);

            user2Repository.save(user2);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }


    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String token = null;

        try{

            String userId = dto.getUserId();
            
            User2 user2 = user2Repository.findByUserId(userId);
            if(user2 == null) return SignInResponseDto.signInFailed();

            String password = dto.getUserPassword();
            String encodedPassword = user2.getUserPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();
            
            token = jwtProvider.create(userId);
            
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(token);
    }

    
    

}
