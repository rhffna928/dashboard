package com.samhwan.dashboard.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterResponseDto;


public interface InverterInterfaceService {


        ResponseEntity<? super GetInverterResponseDto> getInverterHistory(
                String userId, Integer invId,
                LocalDate from, LocalDate to,
                Integer intervalMinutes, int page,
                int size);

        ResponseEntity<? super GetUserInverterResponseDto> getUserInverterLast(
                String userId, Integer invId, Integer plantId);

        ResponseEntity<? super GetUserHeaderResponseDto> getUserInverterheader(String userId);


}
