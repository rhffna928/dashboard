package com.samhwan.dashboard.dto.request.plant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePlantRequestDto {
    
    private Integer plantCode; // plant_code
    private String plantName;
    private String plantOwner;
    private String plantMan;
    private String userId; // nullable
    private String plantCapacity;
    private String plantPrice;
    private String address;
    // DB default가 있지만, 프론트에서 보내도 됨 (없으면 서비스/엔티티에서 기본값 처리)
    private String lat; // default "35"
    private String lng; // default "127"
    // DB default가 있지만, 명시적으로 보내고 싶으면 사용
    private String useYn;  // default "Y"
    private String smsYn;  // default "N"
    private String infoYn; // default "N"
    private String startYmd;
    private String startYear;
    private String moduleInfo;
    private String invInfo;
    private Integer getDataSec;
    private Double yesGen;
    private Double monthGen;
    
}
