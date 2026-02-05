package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.request.inverter.UpdateInverterListRequestDto;
import com.samhwan.dashboard.dto.response.inverter_list.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.DeleteInverterListResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetUserInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.UpdateInverterListResponseDto;


public interface InverterList2Service {

    ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2();

    ResponseEntity<? super CreateInverterResponseDto> createInverter(CreateInverterRequestDto requestBody);

    ResponseEntity<? super UpdateInverterListResponseDto> updateInverter(String currentUserId, Integer id,
            UpdateInverterListRequestDto requestBody);

    ResponseEntity<? super DeleteInverterListResponseDto> deleteInverter(Integer id);

    
    ResponseEntity<? super GetUserInverterList2ResponseDto> getUserInverterList2(String userId);

}