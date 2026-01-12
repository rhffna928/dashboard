package com.samhwan.dashboard.dto.request.inverter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateInverterListRequestDto {

    @NotNull
    private Integer plantId;     // 발전소 아이디
    @NotNull
    private Integer groupId;     // 그룹 아이디
    @NotNull
    private Integer unitId;      // 국번
    private String invId;
    private String invName;
    private String invType;
    private String invModel;
    private String invProtocol;
    @NotNull
    private Double invCapacity; // double(22,1)
    @NotNull
    private Double minPower;
    @NotNull
    private Double maxPower;
    @NotNull
    private Double todayGen;
    private Double totalGen;
    private String useYn;
    private String invFault;
    @NotNull
    private Integer mccbId;      // DEFAULT 0
    @NotNull
    private Integer mccbStatus;

}
