package com.samhwan.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.response.inverter.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.response.inverter.GetInverterList2ResponseDto;
import com.samhwan.dashboard.service.InverterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/invt")
@RequiredArgsConstructor
public class InverterController {

    private final InverterService inverterService;

    @GetMapping("")
    public ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2() {
        System.out.println("hello");
        return inverterService.getInverterList2();
    }
    @PostMapping("create")
    public ResponseEntity<? super CreateInverterResponseDto> createInverter(
        @RequestBody @Valid CreateInverterRequestDto requestBody
    ) {
        System.out.println("hello");
        ResponseEntity<? super CreateInverterResponseDto> response = inverterService.createInverter(requestBody);
        return response;
        // {
        //     "plantId": 0,
        //     "groupId": 0,
        //     "unitId": 1,
        //     "invId": "1",
        //     "invName": "123",
        //     "invType": "test",
        //     "invModel": "test",
        //     "invProtocol": "test",
        //     "invCapacity": 0.0,
        //     "minPower": 0.0,
        //     "maxPower": 0.0,
        //     "todayGen": 0.0,
        //     "totalGen": 0.0,
        //     "useYn": "N",
        //     "invFault": "0",
        //     "mccbId": 0,
        //     "mccbStatus": 0
        // }
    }
}
