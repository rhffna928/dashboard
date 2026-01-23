package com.samhwan.dashboard.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.response.inverter.GetInverterResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.AlarmRepository;
import com.samhwan.dashboard.repository.InverterRepository;
import com.samhwan.dashboard.service.InverterInterfaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InverterServiceImpl implements InverterInterfaceService {

    private final InverterRepository inverterRepository;

    @Override
    public ResponseEntity<? super GetInverterResponseDto> getInverter(
        String userId
    ) {

        List<Inverter> result = inverterRepository.(userId);
        
        return GetInverterResponseDto.success(result);

    }

}
