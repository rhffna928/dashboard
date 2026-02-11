package com.samhwan.dashboard.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.request.plant.UpdatePlantListRequestDto;
import com.samhwan.dashboard.dto.response.plant_list.GetPlantList2ResponseDto;
import com.samhwan.dashboard.dto.response.plant_list.GetUserPlantList2ResponseDto;
import com.samhwan.dashboard.dto.response.plant_list.UpdatePlantListResponseDto;
import com.samhwan.dashboard.entity.PlantList2;
import com.samhwan.dashboard.entity.User2;
import com.samhwan.dashboard.repository.AdminRepository;
import com.samhwan.dashboard.repository.PlantList2Repository;
import com.samhwan.dashboard.service.PlantInterfaceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantInterfaceService {

    private final PlantList2Repository plantList2Repository;
    private final AdminRepository adminRepository;

    private boolean isAdmin(User2 user) {
        // auth == 5 이 관리자라면
        return user != null && "5".equals(user.getAuth());
    }

    @Override
    public ResponseEntity<? super GetPlantList2ResponseDto> getPlants() {
        try{
            List<PlantList2> plantList2s = plantList2Repository.findAll();
            return GetPlantList2ResponseDto.success(plantList2s);
        }catch(Exception e){
            e.printStackTrace();
            return GetPlantList2ResponseDto.databaseError();
        }
    }

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

    @Transactional
    @Override
    public ResponseEntity<? super UpdatePlantListResponseDto> updatePlantList(String currentUserId, Integer id,
            UpdatePlantListRequestDto req) {
        User2 currentUser = adminRepository.findByUserId(currentUserId).orElse(null);
        if(!isAdmin(currentUser)) return UpdatePlantListResponseDto.permit();
        PlantList2 targetId = plantList2Repository.findById(id).orElse(null);
        targetId.updateFrom(req);
        return UpdatePlantListResponseDto.success();
    }

    

   

}
