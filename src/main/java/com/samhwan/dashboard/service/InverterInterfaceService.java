package com.samhwan.dashboard.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;

public interface InverterInterfaceService {


        public ResponseEntity<? super GetInverterResponseDto> getInverterHistory(
                String userId, Integer invId,
                LocalDate from, LocalDate to,
                Integer intervalMinutes, int page,
                int size);

}
