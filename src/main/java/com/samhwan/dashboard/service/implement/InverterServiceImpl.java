package com.samhwan.dashboard.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterList2ResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.entity.InverterList2;
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
        Integer invId,
        LocalDate from,
        LocalDate to,
        Integer intervalMinutes,
        int page,
        int size
    ) {
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toExclusive = to.plusDays(1).atStartOfDay();        
        int safePage = Math.max(page, 0);
        int safeSize = clamp(size, 1, 200);
        

        Page<Inverter> result = inverterRepository.findInverterHistory(
            userId, invId, fromDt, toExclusive, intervalMinutes, PageRequest.of(safePage, safeSize)
        );
        
        return GetInverterResponseDto.success(result);

    }

    @Override
    public ResponseEntity<? super GetUserInverterList2ResponseDto> getUserInverterList2(
        String userId
    ) {
        try{
            List<InverterList2> list = inverterRepository.findAllByUserId(userId);
            return GetUserInverterList2ResponseDto.success(list);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterList2ResponseDto.databaseError();

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
