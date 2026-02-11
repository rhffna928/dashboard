package com.samhwan.dashboard.dto.response.plant_list;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UpdatePlantListResponseDto extends ResponseDto {
    
    private UpdatePlantListResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);            
    }
    public static ResponseEntity<UpdatePlantListResponseDto> success(){
        UpdatePlantListResponseDto result =  new UpdatePlantListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateId(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    public static ResponseEntity<ResponseDto> permit(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
