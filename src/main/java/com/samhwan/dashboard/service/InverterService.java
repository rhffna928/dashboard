package com.samhwan.dashboard.service;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmListResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.InverterRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InverterService {

    private final InverterRepository inverterRepository;

    public List<Inverter> getLatestByPlant(Integer plantId) {
        return inverterRepository.findByPlantIdOrderByRecvTimeDesc(plantId);
    }

    // 오늘 데이터(00:00~23:59) 그래프용
    public List<Inverter> getTodaySeries(Integer plantId, Integer invId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay().minusSeconds(1);

        return inverterRepository.findByPlantIdAndInvIdAndRecvTimeBetweenOrderByRecvTimeAsc(
                plantId, invId, start, end
        );
    }

    public List<Inverter> getRecentSeries(Integer plantId, Integer invId, int limit) {
        // 최신순 limit개 -> 그래프는 시간순이 보기 좋아서 reverse 하거나, asc로 다시 정렬
        var list = inverterRepository.findByPlantIdAndInvIdOrderByRecvTimeDesc(
                plantId, invId, PageRequest.of(0, limit)
        );
        // 최신순으로 오니 그래프는 오래된->최신으로 뒤집기
        java.util.Collections.reverse(list);
        return list;
    }


}
