package com.samhwan.dashboard.service.implement;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmDeviceIdOptionsResponseDto;
import com.samhwan.dashboard.dto.response.alarm.GetAlarmListResponseDto;
import com.samhwan.dashboard.entity.Alarm;
import com.samhwan.dashboard.repository.AlarmRepository;
import com.samhwan.dashboard.service.AlarmService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    
    private final AlarmRepository alarmRepository;

    @Override
    public ResponseEntity<? super GetAlarmListResponseDto> getAlarmList(
        String userId,
        Integer plantId,
        LocalDate from,
        LocalDate to,
        String deviceType,
        String deviceId,
        int page,
        int size
    ) {
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toExclusive = to.plusDays(1).atStartOfDay();

        String dt = normalizeToAll(deviceType);
        String di = normalizeToAll(deviceId);

        int safePage = Math.max(page, 0);
        int safeSize = clamp(size, 1, 200);

        Page<Alarm> result = alarmRepository.findAlarmList(
            userId, plantId, fromDt, toExclusive, dt, di, PageRequest.of(safePage, safeSize)
        );
        
        return GetAlarmListResponseDto.success(result);

    }
    @Override
    public ResponseEntity<? super GetAlarmDeviceIdOptionsResponseDto> getDeviceIdOptions(
        String userId,
        Integer plantId,
        LocalDate from,
        LocalDate to,
        String deviceType
    ) {
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toExclusive = to.plusDays(1).atStartOfDay();

        String dt = normalizeToAll(deviceType);

        try{
            List<String> deviceIds = alarmRepository.findDeviceIdOptions(
            userId, plantId, fromDt, toExclusive, dt
            );
            return GetAlarmDeviceIdOptionsResponseDto.success(deviceIds);
        }catch(Exception e){
            e.printStackTrace();
            return GetAlarmDeviceIdOptionsResponseDto.databaseError();
        }
        
    }
  

    private String normalizeToAll(String v) {
        if (v == null || v.isBlank()) return "ALL";
        return v;
    }

    private int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
