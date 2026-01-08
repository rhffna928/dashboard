package com.samhwan.dashboard.dto.response.plant;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantResponseDto {

    private Integer id;          // plantId
    private String name;         // plantName
    private String connectUrl;   // plantUrl
    private String capacityKw;   // plantCapacity
    private Double monthlyGen;   // monthGen
    private String plantPrice;   // plantPrice
    private String address;
    private String lat;
    private String lng;

    private String activeYn;     // useYN
    private String meterYn;      // cbYN
    private String sensorYn;     // senYN
    private String accessIpYn;   // mjbYN

    private LocalDateTime createdAt; // regdate
}
