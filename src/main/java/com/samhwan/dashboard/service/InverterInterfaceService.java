package com.samhwan.dashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.inverter.GetInverterHistoryResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetReportResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterLatestListResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterKpiResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterSeriesResponseDto;
import com.samhwan.dashboard.entity.Inverter;


public interface InverterInterfaceService {


        ResponseEntity<? super GetInverterHistoryResponseDto> getInverterHistory(
                String userId, Integer plantId, Integer invId, 
                LocalDate from, LocalDate to,
                Integer bucketSec, int page,
                int size);

        ResponseEntity<? super GetUserInverterKpiResponseDto> getUserInverterLast(
                String userId, Integer invId, Integer plantId);

        ResponseEntity<? super GetUserHeaderResponseDto> getUserInverterHeader(String userId);



        ResponseEntity<? super GetUserInverterLatestListResponseDto> getLatestList(
                String userId,
                Integer plantId,
                Integer invId
        );

        ResponseEntity<? super GetUserInverterSeriesResponseDto> getRecentSeries(
                String userId,
                Integer plantId,
                Integer invId
        );
}
