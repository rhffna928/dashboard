package com.samhwan.dashboard.dto.request.inverter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateInverterRequestDto {
    
    @NotNull
    private Integer plantId;     // 발전소 아이디
    @NotNull
    private Integer groupId;     // 그룹 아이디
    @NotNull
    private Integer unitId;      // 국번
    @NotBlank
    private String invId;
    @NotBlank
    private String invName;
    @NotBlank
    private String invType;
    @NotBlank
    private String invModel;
    @NotBlank
    private String invProtocol;
    @NotNull
    private Double invCapacity; // double(22,1)
    @NotNull
    private Double minPower;
    @NotNull
    private Double maxPower;
    @NotNull
    private Double todayGen;
    @NotNull
    private Double totalGen;
    @NotBlank
    private String useYn;
    @NotBlank
    private String invFault;
    @NotNull
    private Integer mccbId;      // DEFAULT 0
    @NotNull
    private Integer mccbStatus;
}
