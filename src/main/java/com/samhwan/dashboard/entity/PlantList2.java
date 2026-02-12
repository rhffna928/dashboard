package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import com.samhwan.dashboard.dto.request.plant.CreatePlantRequestDto;
import com.samhwan.dashboard.dto.request.plant.PlantUpdateRequestDto;
import com.samhwan.dashboard.dto.request.plant_list.CreatePlantListRequestDto;
import com.samhwan.dashboard.dto.request.plant_list.UpdatePlantListRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plant_list2")
public class PlantList2 {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Integer plantId;

    @Column(name = "plant_code", nullable = false)
    private Integer plantCode; // DMT 등록코드

    @Column(name = "plant_name", nullable = false, length = 50)
    private String plantName;

    @Column(name = "plant_owner", nullable = false, length = 50)
    private String plantOwner; // 소유주

    @Column(name = "plant_man", nullable = false, length = 50)
    private String plantMan; // 전문기업

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "plant_capacity", nullable = false, length = 50)
    private String plantCapacity; // 발전용량

    @Column(name = "plant_price", nullable = false, length = 50)
    private String plantPrice; // 발전단가

    @Column(name = "address", nullable = false, length = 50)
    private String address; // 주소

    @Column(name = "lat", nullable = false, length = 50)
    private String lat = "35"; // 위도

    @Column(name = "lng", nullable = false, length = 50)
    private String lng = "127"; // 경도

    @Column(name = "inv_count", nullable = false)
    private Integer invCount; // 인버터 개수

    @Column(name = "inv_count_disable", nullable = false)
    private Integer invCountDisable; // 인버터 비활성화 개수

    @Column(name = "use_yn", nullable = false, length = 50)
    private String useYn = "Y"; // 사용/미사용

    @Column(name = "sms_yn", nullable = false, length = 50)
    private String smsYn = "N";

    @Column(name = "info_yn", nullable = false, length = 50)
    private String infoYn = "N";

    @Column(name = "start_ymd", nullable = false, length = 50)
    private String startYmd; // 발전데이터 적산시작날짜

    @Column(name = "start_year", nullable = false, length = 50)
    private String startYear; // 준공년도

    @Column(name = "module_info", nullable = false, length = 50)
    private String moduleInfo; // 모듈정보

    @Column(name = "inv_info", nullable = false, length = 50)
    private String invInfo; // 인버터정보

    @Column(name = "get_data_sec", nullable = false)
    private Integer getDataSec; // 데이터수집주기(초)

    @Column(name = "yes_gen", nullable = false)
    private Double yesGen;

    @Column(name = "month_gen", nullable = false)
    private Double monthGen;

    /**
     * MySQL default current_timestamp()가 자동으로 채우는 컬럼이라면:
     * - insert 시 값 넣지 않도록 insertable=false
     * - update 시 값 변경하지 않도록 updatable=false
     */
    @Column(name = "regdate", nullable = false, insertable = false, updatable = false)
    private LocalDateTime regdate;

    @Builder
    public PlantList2(CreatePlantListRequestDto dto) {
        this.plantCode = dto.getPlantCode();
        this.plantName = dto.getPlantName();
        this.plantOwner = dto.getPlantOwner();
        this.plantMan = dto.getPlantMan();
        this.userId = dto.getUserId();
        this.plantCapacity = dto.getPlantCapacity();
        this.plantPrice = dto.getPlantPrice();
        this.address = dto.getAddress();

        // DB default가 있지만, DTO가 null이면 기본값으로 보정
        this.lat = (dto.getLat() == null || dto.getLat().isBlank()) ? "35" : dto.getLat();
        this.lng = (dto.getLng() == null || dto.getLng().isBlank()) ? "127" : dto.getLng();

        this.useYn = (dto.getUseYn() == null || dto.getUseYn().isBlank()) ? "Y" : dto.getUseYn();
        this.smsYn = (dto.getSmsYn() == null || dto.getSmsYn().isBlank()) ? "N" : dto.getSmsYn();
        this.infoYn = (dto.getInfoYn() == null || dto.getInfoYn().isBlank()) ? "N" : dto.getInfoYn();

        this.startYmd = dto.getStartYmd();
        this.startYear = dto.getStartYear();
        this.moduleInfo = dto.getModuleInfo();
        this.invInfo = dto.getInvInfo();
        this.getDataSec = dto.getGetDataSec();
        this.yesGen = dto.getYesGen();
        this.monthGen = dto.getMonthGen();

        // ✅ invCount는 인버터 CRUD에서 트랜잭션으로 관리하는 게 안전 (초기 0)
        this.invCount = 0;
        this.invCountDisable = 0;
    }

 
    public void updateFrom(UpdatePlantListRequestDto dto) {
        // plantId, regdate는 수정 대상 아님
        this.plantCode = dto.getPlantCode();
        this.plantName = dto.getPlantName();
        this.plantOwner = dto.getPlantOwner();
        this.plantMan = dto.getPlantMan();
        this.userId = dto.getUserId();
        this.plantCapacity = dto.getPlantCapacity();
        this.plantPrice = dto.getPlantPrice();
        this.address = dto.getAddress();

        // null/blank이면 기존값 유지
        if (dto.getLat() != null && !dto.getLat().isBlank()) this.lat = dto.getLat();
        if (dto.getLng() != null && !dto.getLng().isBlank()) this.lng = dto.getLng();

        if (dto.getUseYn() != null && !dto.getUseYn().isBlank()) this.useYn = dto.getUseYn();
        if (dto.getSmsYn() != null && !dto.getSmsYn().isBlank()) this.smsYn = dto.getSmsYn();
        if (dto.getInfoYn() != null && !dto.getInfoYn().isBlank()) this.infoYn = dto.getInfoYn();

        this.startYmd = dto.getStartYmd();
        this.startYear = dto.getStartYear();
        this.moduleInfo = dto.getModuleInfo();
        this.invInfo = dto.getInvInfo();
        this.getDataSec = dto.getGetDataSec();
        this.yesGen = dto.getYesGen();
        this.monthGen = dto.getMonthGen();
    }
}

