package com.samhwan.dashboard.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.InverterRepository;
import com.samhwan.dashboard.service.InverterInterfaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InverterServiceImpl implements InverterInterfaceService {

    private final InverterRepository inverterRepository;


    @Override
    public ResponseEntity<? super GetInverterResponseDto> getInverterHistory(
        String userId,
        Integer plantId,
        Integer invId,
        LocalDate from,
        LocalDate to,
        String deviceType,
        String deviceId,
        Integer intervalMinutes,
        int page,
        int size
    ) {
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toExclusive = to.plusDays(1).atStartOfDay();        
        int safePage = Math.max(page, 0);
        int safeSize = clamp(size, 1, 200);
        
        int iv = (intervalMinutes == null ? 60 : intervalMinutes);

        Page<Inverter> result = inverterRepository.findInverterHistory(
            userId, plantId, invId, fromDt, toExclusive, deviceType, deviceId, iv, PageRequest.of(safePage, safeSize)
        );
        
        return GetInverterResponseDto.success(result);

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
