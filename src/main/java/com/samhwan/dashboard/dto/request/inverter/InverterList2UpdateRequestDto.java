package com.samhwan.dashboard.dto.request.inverter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverterList2UpdateRequestDto {

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
    @PositiveOrZero
    private Double invCapacity; // double(22,1)
    @NotNull
    @PositiveOrZero
    private Double minPower;
    @NotNull
    @PositiveOrZero
    private Double maxPower;
    @NotNull
    @PositiveOrZero
    private Double todayGen;
    @NotNull
    @PositiveOrZero
    private Double totalGen;
    @NotBlank
    private String useYn;
    @NotBlank
    private String invFault;
    @NotNull
    @PositiveOrZero
    private Integer mccbId;      // DEFAULT 0
    @NotNull
    private Integer mccbStatus;

}
