package com.samhwan.dashboard.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.plant.GetUserPlantList2ResponseDto;
import com.samhwan.dashboard.entity.PlantList2;
import com.samhwan.dashboard.repository.PlantList2Repository;
import com.samhwan.dashboard.service.PlantInterfaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantInterfaceService {

    private final PlantList2Repository plantList2Repository;

    @Override
    public ResponseEntity<? super GetUserPlantList2ResponseDto> getUserPlantList2(String userId){

        try{
            List<PlantList2> plantList2s = plantList2Repository.findAllByUserId(userId);
            return GetUserPlantList2ResponseDto.success(plantList2s);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserPlantList2ResponseDto.databaseError();

        }
    }


}
