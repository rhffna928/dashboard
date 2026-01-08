package com.samhwan.dashboard.dto.response.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.User2;

import lombok.Getter;

@Getter
public class UpdateUserResponseDto extends ResponseDto {
    
    private String userId;
    private String userName;
    private String memo;
    private String auth;
    private String phone;
    private String email;

    private UpdateUserResponseDto(User2 user2){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = user2.getUserId();
        this.userName = user2.getUserName();
        this.memo = user2.getMemo();
        this.auth = user2.getAuth();
        this.phone = user2.getPhone();
        this.email = user2.getEmail();
    }

    public static ResponseEntity<UpdateUserResponseDto> success(User2 user2){
        UpdateUserResponseDto result =  new UpdateUserResponseDto(user2);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    public static ResponseEntity<ResponseDto> permit(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
