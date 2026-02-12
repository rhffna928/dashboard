package com.samhwan.dashboard.dto.response.plant_list;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class CreatePlantListResponseDto extends ResponseDto {

    private CreatePlantListResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);            
    }
    public static ResponseEntity<CreatePlantListResponseDto> success(){
        CreatePlantListResponseDto result = new CreatePlantListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateId(){
        ResponseDto result =  new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
