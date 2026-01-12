package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.response.inverter.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetInverterList2ResponseDto;


public interface InverterService {

    ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2();

    ResponseEntity<? super CreateInverterResponseDto> createInverter(CreateInverterRequestDto requestBody);

    
}