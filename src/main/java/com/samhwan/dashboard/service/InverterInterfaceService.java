package com.samhwan.dashboard.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterList2ResponseDto;

public interface InverterInterfaceService {


        ResponseEntity<? super GetInverterResponseDto> getInverterHistory(
                String userId, Integer invId,
                LocalDate from, LocalDate to,
                Integer intervalMinutes, int page,
                int size);

        ResponseEntity<? super GetUserInverterList2ResponseDto> getUserInverterList2(String userId);

}
