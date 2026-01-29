package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
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
    // 발전소별 인버터 목록(최신 데이터들)
    @GetMapping("/plant/{plantId}")
    public List<Inverter> getInvertersByPlant(@PathVariable("plantId") Integer plantId) {
        return inverterService.getLatestByPlant(plantId);
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


    @GetMapping("")
    public ResponseEntity<? super GetInverterResponseDto> getInverterHistory(
        @AuthenticationPrincipal String userId,
        @RequestParam(name = "invId", required = false) Integer invId,
        @RequestParam(name = "from")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam(name = "to")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(name = "bucketSec") Integer bucketSec,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "20") int size

    ) {
        System.out.println(bucketSec);
        return inverterInterfaceService.getInverterHistory(userId, invId, from, to, bucketSec, page, size);
    }

}
