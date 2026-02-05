package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.dto.response.inverter.*;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.service.InverterInterfaceService;
import com.samhwan.dashboard.service.InverterService;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inverters")
@RequiredArgsConstructor
public class InverterController {

    private final InverterService inverterService;
    private final InverterInterfaceService inverterInterfaceService;
    

    @GetMapping("/lastest")
    public ResponseEntity<? super GetUserInverterLatestListResponseDto> getLatestList(
        @AuthenticationPrincipal String userId,
        @RequestParam(name = "plantId", required = false) Integer plantId,
        @RequestParam(name = "invId", required = false) Integer invId
    ) {
        return InverterInterfaceService.getLatestList(userId, plantId, invId);
    }

    @GetMapping("/series/recent")
    public ResponseEntity<? super GetUserInverterSeriesResponseDto> getRecentSeries(
        @AuthenticationPrincipal String userId,
        @RequestParam(name = "plantId", required = false) Integer plantId,
        @RequestParam(name = "invId", required = false) Integer invId
    ) {
        return InverterInterfaceService.getRecentSeries(userId, plantId, invId);
    }
    
    // 그래프용(오늘 시계열)
    @GetMapping("/plant/{plantId}/inv/{invId}/today")
    public List<Inverter> getTodaySeries(
            @PathVariable("plantId") Integer plantId,
            @PathVariable("invId") Integer invId
    ) {
        return inverterService.getTodaySeries(plantId, invId);
    }

    @GetMapping("/plant/{plantId}/inv/{invId}/recent")
    public List<Inverter> getRecentSeries(
            @PathVariable("plantId") Integer plantId,
            @PathVariable("invId") Integer invId,
            @RequestParam(name = "limit", defaultValue = "200") Integer limit
    ) {
        return inverterService.getRecentSeries(plantId, invId, limit);
    }


    @GetMapping("history")
    public ResponseEntity<? super GetInverterHistoryResponseDto> getInverterHistory(
        @AuthenticationPrincipal String userId,
        @RequestParam(name = "plantId", required = false) Integer plantId,
        @RequestParam(name = "invId", required = false) Integer invId,
        @RequestParam(name = "from")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam(name = "to")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(name = "bucketSec") Integer bucketSec,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "20") int size

    ) {
        System.out.println(bucketSec);
        return inverterInterfaceService.getInverterHistory(userId,plantId, invId, from, to, bucketSec, page, size);
    }


    @GetMapping("/kpi")
    public ResponseEntity<? super GetUserInverterKpiResponseDto> getUserInverterLast(
        @AuthenticationPrincipal String userId,
        @RequestParam(name = "invId", required = false) Integer invId,
        @RequestParam(name = "plantId", required = false) Integer plantId
    ) {
        return inverterInterfaceService.getUserInverterLast(userId,invId,plantId);
    }
    @GetMapping("/header")
    public ResponseEntity<? super GetUserHeaderResponseDto> getUserInverterHeader(
        @AuthenticationPrincipal String userId
    ) {
        return inverterInterfaceService.getUserInverterHeader(userId);
    }

}
