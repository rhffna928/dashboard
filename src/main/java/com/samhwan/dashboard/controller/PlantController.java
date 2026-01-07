package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.dto.response.PlantResponseDto;
import com.samhwan.dashboard.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping
    public List<PlantResponseDto> getPlants() {
        return plantService.getPlants();
    }
}
