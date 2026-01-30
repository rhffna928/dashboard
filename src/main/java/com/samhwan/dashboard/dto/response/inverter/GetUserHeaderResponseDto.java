package com.samhwan.dashboard.dto.response.inverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.Inverter;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserHeaderResponseDto extends ResponseDto{

    
    private final InverterHeader inverterHeader;

    private GetUserHeaderResponseDto(InverterHeader inverterHeader) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.inverterHeader = inverterHeader;
    }
    public static ResponseEntity<GetUserHeaderResponseDto> success(InverterHeader inverterHeader){
        GetUserHeaderResponseDto result =  new GetUserHeaderResponseDto(inverterHeader);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Getter
    @Builder
    public static class InverterHeader {
        private Double currentPowerKw;
    

        public InverterHeader(Double currentPowerKw) {
        this.currentPowerKw = currentPowerKw;
        }

    }

}