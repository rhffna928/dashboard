package com.samhwan.dashboard.service;

import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.dto.response.plant.GetUserPlantList2ResponseDto;

public interface PlantInterfaceService {

    ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(String userId);

    
}

