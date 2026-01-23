package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.service.InverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inverters")
@RequiredArgsConstructor
public class InverterController {

    private final InverterService inverterService;

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

}
