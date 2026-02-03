package com.samhwan.dashboard.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.List;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetReportResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.DashboardKpiView;
import com.samhwan.dashboard.repository.InverterHistoryView;
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
        Integer bucketSec,
        int page,
        int size
    ) {
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toExclusive = to.plusDays(1).atStartOfDay();        
        int safePage = Math.max(page, 0);
        int safeSize = clamp(size, 1, 200);
        

        Page<InverterHistoryView> result = inverterRepository.findInverterHistory(
            userId, invId, fromDt, toExclusive, bucketSec, PageRequest.of(safePage, safeSize)
        );
        
        return GetInverterResponseDto.success(result);

    }


    @Override
    public ResponseEntity<? super GetUserInverterResponseDto> getUserInverterLast(
        String userId, Integer invId, Integer plantId) {
        try{
            DashboardKpiView v = inverterRepository.findLatestByUserIdAndInvId(userId, invId,plantId).orElse(null);
            GetUserInverterResponseDto.DashboardKpi kpi =
                GetUserInverterResponseDto.DashboardKpi.builder()
                    .genHours(v.getGenHours())
                    .totalGenKwh(v.getTotalGenKwh())
                    .monthGenKwh(v.getMonthGenKwh())
                    .yesterdayGenKwh(v.getYesterdayGenKwh())
                    .todayGenKwh(v.getTodayGenKwh())
                    .currentPowerKw(v.getCurrentPowerKw())
                    .build();
            return GetUserInverterResponseDto.success(kpi);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterResponseDto.databaseError();

        }
    }


    @Override
    public ResponseEntity<? super GetUserHeaderResponseDto> getUserInverterHeader(String userId) {
        try{
            GetUserHeaderResponseDto.InverterHeader inv = inverterRepository.getCurrentPower(userId);
            return GetUserHeaderResponseDto.success(inv);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserHeaderResponseDto.databaseError();
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
