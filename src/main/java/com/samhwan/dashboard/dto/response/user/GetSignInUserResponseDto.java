package com.samhwan.dashboard.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.User2;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {

    private String userId;
    private String userName;
    // private String memo;
    // private String auth;
    // private String phone;
    // private String email;

    private GetSignInUserResponseDto(User2 user2){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = user2.getUserId();
        this.userName = user2.getUserName();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(User2 user2){
        GetSignInUserResponseDto result =  new GetSignInUserResponseDto(user2);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
