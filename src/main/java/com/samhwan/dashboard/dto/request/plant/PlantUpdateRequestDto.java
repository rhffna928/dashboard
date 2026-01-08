package com.samhwan.dashboard.dto.request.plant;

import lombok.Getter;

@Getter
public class PlantUpdateRequestDto {

    private String name;
    private String connectUrl;
    private String capacityKw;
    private Double monthlyGen;
    private String plantPrice;
    private String address;
    private String lat;
    private String lng;

    private String activeYn;
    private String meterYn;
    private String sensorYn;
    private String accessIpYn;
}
