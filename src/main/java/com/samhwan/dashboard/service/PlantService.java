package com.samhwan.dashboard.service;

import com.samhwan.dashboard.dto.response.PlantResponseDto;
import com.samhwan.dashboard.entity.PlantList;
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
                        p.getAddress(),
                        p.getLat(),
                        p.getLng(),
                        p.getUseYN(),
                        p.getCbYN(),
                        p.getSenYN(),
                        p.getMjbYN(),
                        p.getRegdate()
                ))
                .toList();
    }
}
