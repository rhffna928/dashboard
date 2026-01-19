package com.samhwan.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmDeviceIdOptionsResponseDto;
import com.samhwan.dashboard.dto.response.alarm.GetAlarmListResponseDto;
import com.samhwan.dashboard.service.AlarmService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/alarm")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;


    @GetMapping("/list")
    public ResponseEntity<? super GetAlarmListResponseDto> getAlarmList(
        @AuthenticationPrincipal String userId,
        @RequestParam(required = false) Integer plantId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(defaultValue = "ALL") String deviceType,
        @RequestParam(defaultValue = "ALL") String deviceId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size

    ) {
        
        System.out.println("userId=" + userId);
        return alarmService.getAlarmList(userId, plantId, from, to, deviceType, deviceId, page, size);
    }
    
    @GetMapping("/device-ids")
    public ResponseEntity<? super GetAlarmDeviceIdOptionsResponseDto> getDeviceIdOptions(
        @AuthenticationPrincipal String userId,
        @RequestParam(required = false) Integer plantId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(defaultValue = "인버터") String deviceType
    ) {
        return alarmService.getDeviceIdOptions(userId, plantId, from, to, deviceType);
    }
    
    

}
