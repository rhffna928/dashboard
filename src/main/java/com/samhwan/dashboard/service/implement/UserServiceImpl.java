package com.samhwan.dashboard.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.user.GetSignInUserResponseDto;
import com.samhwan.dashboard.entity.User2;
import com.samhwan.dashboard.repository.User2Repository;
import com.samhwan.dashboard.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final User2Repository user2Repository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {

        User2 user2 = null;

        try{
            user2 = user2Repository.findByUserId(userId);
            if (user2 == null) return GetSignInUserResponseDto.notExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInUserResponseDto.success(user2);
    }



}
