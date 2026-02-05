package com.samhwan.dashboard.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.request.inverter.UpdateInverterListRequestDto;
import com.samhwan.dashboard.dto.response.inverter_list.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.DeleteInverterListResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetUserInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.UpdateInverterListResponseDto;
import com.samhwan.dashboard.service.InverterList2Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/invt_list2")
@RequiredArgsConstructor
public class InverterListController {

    private final InverterList2Service inverterListService;

    @GetMapping("")
    public ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2() {
        return inverterListService.getInverterList2();
    }
    @PostMapping("create")
    public ResponseEntity<? super CreateInverterResponseDto> createInverter(
        @RequestBody @Valid CreateInverterRequestDto requestBody
    ) {

        ResponseEntity<? super CreateInverterResponseDto> response = inverterListService.createInverter(requestBody);
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
    @PutMapping("{id}")
    public ResponseEntity<? super UpdateInverterListResponseDto> updateInverter(
        Principal principal,
        @PathVariable("id") Integer id,
        @Valid @RequestBody UpdateInverterListRequestDto requestBody
    ) {
        String currentUserId = principal.getName();


        System.out.println(requestBody);
        System.out.println(currentUserId);
        System.out.println(id);
        ResponseEntity<? super UpdateInverterListResponseDto> response = inverterListService.updateInverter(currentUserId,id,requestBody);

        return response;

    }
    @DeleteMapping("{id}")
    public ResponseEntity<? super DeleteInverterListResponseDto> deleteInverter(
        @PathVariable("id") Integer id
    ) {

        ResponseEntity<? super DeleteInverterListResponseDto> response = inverterListService.deleteInverter(id);
        return response;

    }

    @GetMapping("/usr")
    public ResponseEntity<? super GetUserInverterList2ResponseDto> getUserInverterList2(
        @AuthenticationPrincipal String userId
    ) {
        return inverterListService.getUserInverterList2(userId);
    }
}
