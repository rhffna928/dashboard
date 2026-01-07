package com.samhwan.dashboard.dto.response.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;


import lombok.Getter;

@Getter
public class DeleteUserResponseDto extends ResponseDto{

    private String userId;

    private DeleteUserResponseDto(String userId){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userId;
    }

    public static ResponseEntity<DeleteUserResponseDto> success(String userId){
        DeleteUserResponseDto result =  new DeleteUserResponseDto(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
