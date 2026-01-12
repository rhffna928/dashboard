package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.request.inverter.UpdateInverterListRequestDto;
import com.samhwan.dashboard.dto.response.inverter.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.UpdateInverterListResponseDto;
import com.samhwan.dashboard.dto.response.inverter.DeleteInverterListResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetInverterList2ResponseDto;


public interface InverterListService {

    ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2();

    ResponseEntity<? super CreateInverterResponseDto> createInverter(CreateInverterRequestDto requestBody);

    ResponseEntity<? super UpdateInverterListResponseDto> updateInverter(String currentUserId, Integer id,
            UpdateInverterListRequestDto requestBody);

    ResponseEntity<? super DeleteInverterListResponseDto> deleteInverter(Integer id);

}