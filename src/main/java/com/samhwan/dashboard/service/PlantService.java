package com.samhwan.dashboard.service;

import com.samhwan.dashboard.entity.PlantList;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import jakarta.transaction.Transactional;
import com.samhwan.dashboard.repository.PlantListRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantListRepository plantListRepository;



    @Transactional
    public void updatePlant(Integer id, PlantUpdateRequestDto req) {
        PlantList plant = plantListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found: " + id));

        plant.updateFrom(req);
    }


    @Transactional
    public void deletePlant(Integer id) {
        plantListRepository.deleteById(id);
    }

}

