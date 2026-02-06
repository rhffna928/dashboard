package com.samhwan.dashboard.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.List;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.inverter.GetInverterHistoryResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterKpiResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterLatestListResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterLatestListResponseDto.InverterLatestRow;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterSeriesResponseDto.InverterView;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterSeriesResponseDto;
import com.samhwan.dashboard.repository.DashboardKpiView;
import com.samhwan.dashboard.repository.InverterHistoryView;
import com.samhwan.dashboard.repository.InverterLastListView;
import com.samhwan.dashboard.repository.InverterRepository;
import com.samhwan.dashboard.repository.InverterSeriesRow;
import com.samhwan.dashboard.service.InverterInterfaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InverterServiceImpl implements InverterInterfaceService {

    private final InverterRepository inverterRepository;


    @Override
    public ResponseEntity<? super GetInverterHistoryResponseDto> getInverterHistory(
        String userId,
        Integer plantId,
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
            userId, plantId, invId, fromDt, toExclusive, bucketSec, PageRequest.of(safePage, safeSize)
        );
        
        return GetInverterHistoryResponseDto.success(result);

    }


    @Override
    public ResponseEntity<? super GetUserInverterKpiResponseDto> getUserInverterLast(
        String userId, Integer invId, Integer plantId) {
        try{
            DashboardKpiView v = inverterRepository.findLatestByUserIdAndInvId(userId, invId,plantId).orElse(null);
            GetUserInverterKpiResponseDto.DashboardKpi kpi =
                GetUserInverterKpiResponseDto.DashboardKpi.builder()
                    .genHours(v.getGenHours())
                    .totalGenKwh(v.getTotalGenKwh())
                    .monthGenKwh(v.getMonthGenKwh())
                    .yesterdayGenKwh(v.getYesterdayGenKwh())
                    .todayGenKwh(v.getTodayGenKwh())
                    .currentPowerKw(v.getCurrentPowerKw())
                    .build();
            return GetUserInverterKpiResponseDto.success(kpi);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterKpiResponseDto.databaseError();

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


    @Override
    public ResponseEntity<? super GetUserInverterLatestListResponseDto> getLatestList(
        String userId,
        Integer plantId,
        Integer invId
    ) {
        try{
            List<Inverter> rows = inverterRepository.getLatestList(userId,plantId,invId);
            List<GetUserInverterLatestListResponseDto.InverterLatestRow> list =
                rows.stream()
                .map(GetUserInverterLatestListResponseDto.InverterLatestRow::fromEntity)
                .toList();
            return GetUserInverterLatestListResponseDto.success(list);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterLatestListResponseDto.databaseError();
        }

    }


    @Override
    public ResponseEntity<? super GetUserInverterSeriesResponseDto> getRecentSeries(
        String userId,
        Integer plantId,
        Integer invId
    ) {
       try{
            List<InverterSeriesRow> rows = inverterRepository.getRecentSeries(userId,plantId,invId);
            List<GetUserInverterSeriesResponseDto.InverterView> list =
                rows.stream()
                    .map(r -> GetUserInverterSeriesResponseDto.InverterView.builder()
                        .bucketHour(r.getBucketHour())
                        .plantId(r.getPlantId())
                        .invId(r.getInvId())
                        .hourGenKwh(r.getHourGenKwh())
                        .samples(r.getSamples())
                        .build())
                    .toList();
            return GetUserInverterSeriesResponseDto.success(list);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterSeriesResponseDto.databaseError();
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
