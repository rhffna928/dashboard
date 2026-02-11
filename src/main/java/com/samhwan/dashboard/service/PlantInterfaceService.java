package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.request.plant.UpdatePlantListRequestDto;
import com.samhwan.dashboard.dto.response.plant_list.GetPlantList2ResponseDto;
import com.samhwan.dashboard.dto.response.plant_list.GetUserPlantList2ResponseDto;
import com.samhwan.dashboard.dto.response.plant_list.UpdatePlantListResponseDto;

public interface PlantInterfaceService {

    ResponseEntity<? super GetPlantList2ResponseDto> getPlants();

    ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(String userId);

    ResponseEntity<? super UpdatePlantListResponseDto> updatePlantList(String currentUserId, Integer id,
            UpdatePlantListRequestDto req);

    

    
}

