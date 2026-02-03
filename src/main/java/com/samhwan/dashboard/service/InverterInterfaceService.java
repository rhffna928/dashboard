package com.samhwan.dashboard.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterHistoryResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetReportResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterResponseDto;


public interface InverterInterfaceService {


        ResponseEntity<? super GetInverterHistoryResponseDto> getInverterHistory(
                String userId, Integer plantId, Integer invId, 
                LocalDate from, LocalDate to,
                Integer bucketSec, int page,
                int size);

        ResponseEntity<? super GetUserInverterResponseDto> getUserInverterLast(
                String userId, Integer invId, Integer plantId);

        ResponseEntity<? super GetUserHeaderResponseDto> getUserInverterHeader(String userId);



}
