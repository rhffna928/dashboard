package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;

public interface InverterInterfaceService {
    
        public ResponseEntity<? super GetInverterResponseDto> getInverter(
        String userId);

}
