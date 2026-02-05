package com.samhwan.dashboard.dto.response.inverter_list;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;


import lombok.Getter;

@Getter
public class DeleteInverterListResponseDto extends ResponseDto{

    private Integer id;

    private DeleteInverterListResponseDto(Integer id){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.id = id;
    }

    public static ResponseEntity<DeleteInverterListResponseDto> success(Integer id){
        DeleteInverterListResponseDto result =  new DeleteInverterListResponseDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}