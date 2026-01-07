package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plant_list")
public class PlantList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Integer plantId;

    @Column(name = "plant_code", nullable = false)
    private Integer plantCode;

    @Column(name = "plant_name", nullable = false, length = 50)
    private String plantName;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "plant_url", nullable = false, length = 50)
    private String plantUrl;

    @Column(name = "plant_capacity", nullable = false, length = 50)
    private String plantCapacity;

    @Column(name = "plant_price", nullable = false, length = 50)
    private String plantPrice;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    // ✅ DB 컬럼이 ipAddr (camelCase) 이라서 그대로 지정
    @Column(name = "ipAddr", nullable = false, length = 50)
    private String ipAddr;

    @Column(name = "inv_count", nullable = false)
    private Integer invCount;

    @Column(name = "inv_count_disable", nullable = false)
    private Integer invCountDisable;

    @Column(name = "cb_count", nullable = false)
    private Integer cbCount;

    @Column(name = "mjb_count", nullable = false)
    private Integer mjbCount;

    // ✅ DB 컬럼이 useYN 등 camelCase 섞임 → 그대로 지정
    @Column(name = "useYN", nullable = false, length = 50)
    private String useYN;

    @Column(name = "cbYN", nullable = false, length = 50)
    private String cbYN;

    @Column(name = "senYN", nullable = false, length = 50)
    private String senYN;

    @Column(name = "mjbYN", nullable = false, length = 50)
    private String mjbYN;

    @Column(name = "trackerYN", nullable = false, length = 50)
    private String trackerYN;

    @Column(name = "trYN", nullable = false, length = 50)
    private String trYN;

    @Column(name = "smsYN", nullable = false, length = 50)
    private String smsYN;

    @Column(name = "diagramYN", nullable = false, length = 50)
    private String diagramYN;

    @Column(name = "infoYN", nullable = false, length = 50)
    private String infoYN;

    // ✅ DB 컬럼이 commErrSec (camelCase) 이라서 그대로 지정
    @Column(name = "commErrSec", nullable = false, length = 50)
    private String commErrSec;

    @Column(name = "startYmd", nullable = false, length = 50)
    private String startYmd;

    @Column(name = "km", nullable = false, length = 50)
    private String km;

    // ✅ DB 컬럼이 co2_unit (snake_case) 이라서 그대로 지정
    @Column(name = "co2_unit", nullable = false, length = 50)
    private String co2Unit;

    @Column(name = "lat", nullable = false, length = 50)
    private String lat;

    @Column(name = "lng", nullable = false, length = 50)
    private String lng;

    @Column(name = "memo", nullable = false, length = 200)
    private String memo;

    @Column(name = "startYear", nullable = false, length = 50)
    private String startYear;

    @Column(name = "module_info", nullable = false, length = 50)
    private String moduleInfo;

    @Column(name = "inv_info", nullable = false, length = 50)
    private String invInfo;

    @Column(name = "getDataSec", nullable = false)
    private Integer getDataSec;

    @Column(name = "uploadSec", nullable = false)
    private Integer uploadSec;

    @Column(name = "reqTryCnt", nullable = false)
    private Integer reqTryCnt;

    @Column(name = "reqDelay", nullable = false)
    private Integer reqDelay;

    @Column(name = "yes_gen", nullable = false)
    private Double yesGen;

    @Column(name = "month_gen", nullable = false)
    private Double monthGen;

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;
}
