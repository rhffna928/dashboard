package com.samhwan.dashboard.dto.request.plant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePlantRequestDto {

    private Integer plantCode;
    private String plantName;
    private String plantOwner;
    private String plantMan;
    private String userId;
    private String plantCapacity;
    private String plantPrice;
    private String address;
    // 수정 시 미전송(null)일 수 있으니 서비스/엔티티에서 “null이면 기존값 유지” 처리 권장
    private String lat;
    private String lng;
    // 수정 시 미전송(null)일 수 있음
    private String useYn;
    private String smsYn;
    private String infoYn;
    private String startYmd;
    private String startYear;
    private String moduleInfo;
    private String invInfo;
    private Integer getDataSec;
    private Double yesGen;
    private Double monthGen;
    
}
