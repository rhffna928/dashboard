package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.plant_list.CreatePlantListRequestDto;
import com.samhwan.dashboard.dto.request.plant_list.UpdatePlantListRequestDto;
import com.samhwan.dashboard.dto.response.plant_list.*;


public interface PlantInterfaceService {

    ResponseEntity<? super GetPlantList2ResponseDto> getPlants();

    ResponseEntity<? super CreatePlantListResponseDto> createPlantList(String currentUserId,
            CreatePlantListRequestDto req);

    ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(String userId);

    ResponseEntity<? super UpdatePlantListResponseDto> updatePlantList(String currentUserId, Integer id,
            UpdatePlantListRequestDto req);

    ResponseEntity<? super DeletePlantListResponseDto> deletePlant(String currentUserId, Integer id);

    

    

    
}

