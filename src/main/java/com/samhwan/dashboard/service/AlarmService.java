package com.samhwan.dashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmDeviceIdOptionsResponseDto;
import com.samhwan.dashboard.dto.response.alarm.GetAlarmListResponseDto;



public interface AlarmService {

    ResponseEntity<? super GetAlarmListResponseDto> getAlarmList(
        String userId,
        Integer plantId,
        LocalDate from,
        LocalDate to,
        String deviceType,
        String deviceId,
        int page,
        int size
    );

    ResponseEntity<? super GetAlarmDeviceIdOptionsResponseDto> getDeviceIdOptions(
        String userId,
        Integer plantId,
        LocalDate from,
        LocalDate to,
        String deviceType
    );
}