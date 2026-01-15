package com.samhwan.dashboard.service;

import com.samhwan.dashboard.dto.response.plant.PlantResponseDto;
import com.samhwan.dashboard.entity.PlantList;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import jakarta.transaction.Transactional;
import com.samhwan.dashboard.repository.PlantListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantListRepository plantListRepository;

    public List<PlantResponseDto> getPlants() {
        return plantListRepository.findAll().stream()
                .map(p -> new PlantResponseDto(
                        p.getPlantId(),
                        p.getPlantName(),
                        p.getPlantUrl(),
                        p.getPlantCapacity(),
                        p.getMonthGen(),
                        p.getPlantPrice(),
                        p.getAddress(),
                        p.getLat(),
                        p.getLng(),
                        p.getInvCount(),
                        p.getUseYN(),
                        p.getCbYN(),
                        p.getSenYN(),
                        p.getMjbYN(),
                        p.getRegdate()
                ))
                .toList();
    }

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

