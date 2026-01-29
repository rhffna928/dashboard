package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmDeviceIdOptionsResponseDto;
import com.samhwan.dashboard.dto.response.plant.*;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import com.samhwan.dashboard.service.PlantInterfaceService;
import com.samhwan.dashboard.service.PlantService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;
    private final PlantInterfaceService plantInterfaceService;

    @GetMapping
    public List<PlantResponseDto> getPlants() {
        return plantService.getPlants();
    }

    @PutMapping("/{id}")
    public void updatePlant(@PathVariable("id") Integer id,
                            @RequestBody PlantUpdateRequestDto req) {
        plantService.updatePlant(id, req);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable("id") Integer id) {
        plantService.deletePlant(id);
    }

    @GetMapping("/usr")
    public ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(
        @AuthenticationPrincipal String userId
    ) {

        return plantInterfaceService.getUserPlantList2(userId);
    }

}
