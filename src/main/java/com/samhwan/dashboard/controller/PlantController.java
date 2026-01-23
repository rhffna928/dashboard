package com.samhwan.dashboard.controller;

import com.samhwan.dashboard.dto.response.plant.PlantResponseDto;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import com.samhwan.dashboard.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

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


}
