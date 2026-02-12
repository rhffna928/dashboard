package com.samhwan.dashboard.controller;


import com.samhwan.dashboard.dto.response.inverter_list.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.plant_list.*;
import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import com.samhwan.dashboard.dto.request.plant_list.CreatePlantListRequestDto;
import com.samhwan.dashboard.dto.request.plant_list.UpdatePlantListRequestDto;
import com.samhwan.dashboard.service.PlantInterfaceService;
import com.samhwan.dashboard.service.PlantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;
    private final PlantInterfaceService plantInterfaceService;

    @GetMapping("")
    public ResponseEntity<? super GetPlantList2ResponseDto> getPlants() {
        return plantInterfaceService.getPlants();
    }
    @PostMapping("create")
    public ResponseEntity<? super CreatePlantListResponseDto> createPlantList(
        Principal principal,
        @RequestBody @Valid CreatePlantListRequestDto req
    ) {
        String currentUserId = principal.getName();
        
        return plantInterfaceService.createPlantList(currentUserId,req);
    }

    // @PutMapping("/{id}")
    // public void updatePlant(@PathVariable("id") Integer id,
    //                         @RequestBody PlantUpdateRequestDto req) {
    //     plantService.updatePlant(id, req);
    // }
    @PutMapping("/{id}")
    public ResponseEntity<? super UpdatePlantListResponseDto> updatePlantList(
    Principal principal,
    @PathVariable("id") Integer id,
    @RequestBody UpdatePlantListRequestDto req) {

        String currentUserId = principal.getName();

        return plantInterfaceService.updatePlantList(currentUserId,id,req);
    }
    // @DeleteMapping("/{id}")
    // public void deletePlant(@PathVariable("id") Integer id) {
    //     plantService.deletePlant(id);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<? super DeletePlantListResponseDto> deletePlant(
    Principal principal,
    @PathVariable("id") Integer id
    ) {

        String currentUserId = principal.getName();

        return plantInterfaceService.deletePlant(currentUserId,id);
    }

    @GetMapping("/usr")
    public ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(
        @AuthenticationPrincipal String userId
    ) {

        return plantInterfaceService.getUserPlantList2(userId);
    }

}
