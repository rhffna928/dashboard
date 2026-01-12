package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;

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
@Entity(name="inverter_list2")
@Table(name="inverter_list2")
public class InverterList2 {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "unit_id", nullable = false)
    private Integer unitId;

    @Column(name = "inv_id", nullable = false, length = 50)
    private String invId;

    @Column(name = "inv_name", nullable = false, length = 50)
    private String invName;

    @Column(name = "inv_type", nullable = false, length = 50)
    private String invType;

    @Column(name = "inv_model", nullable = false, length = 50)
    private String invModel;

    @Column(name = "inv_protocol", nullable = false, length = 50)
    private String invProtocol;

    @Column(name = "inv_capacity", nullable = false)
    private Double invCapacity;

    @Column(name = "min_power", nullable = false)
    private Double minPower;

    @Column(name = "max_power", nullable = false)
    private Double maxPower;

    @Column(name = "today_gen", nullable = false)
    private Double todayGen;

    @Column(name = "total_gen", nullable = false)
    private Double totalGen;

    @Column(name = "use_yn", nullable = false, length = 50)
    private String useYn;

    @Column(name = "inv_fault", nullable = false, length = 50)
    private String invFault;

    @Column(name = "mccb_id")
    private Integer mccbId;

    @Column(name = "mccb_status", nullable = false)
    private Integer mccbStatus;

    @Column(name = "regdate", insertable = false, updatable = false)
    private LocalDateTime regdate;


    @Builder
    public InverterList2(CreateInverterRequestDto dto) {
        this.plantId = dto.getPlantId();
        this.groupId = dto.getGroupId();
        this.unitId = dto.getUnitId();
        this.invId = dto.getInvId();
        this.invName = dto.getInvName();
        this.invType = dto.getInvType();
        this.invModel = dto.getInvModel();
        this.invProtocol = dto.getInvProtocol();

        // DDL DEFAULT 0.0 고려: dto가 null일 가능성 있으면 방어
        this.invCapacity = dto.getInvCapacity() != null ? dto.getInvCapacity() : 0.0;
        this.minPower    = dto.getMinPower() != null ? dto.getMinPower() : 0.0;
        this.maxPower    = dto.getMaxPower() != null ? dto.getMaxPower() : 0.0;
        this.todayGen    = dto.getTodayGen() != null ? dto.getTodayGen() : 0.0;
        this.totalGen    = dto.getTotalGen() != null ? dto.getTotalGen() : 0.0;

        this.useYn = dto.getUseYn();
        this.invFault = dto.getInvFault();
        // DDL DEFAULT 0 고려
        this.mccbId = dto.getMccbId() != null ? dto.getMccbId() : 0;

        this.mccbStatus = dto.getMccbStatus() != null ? dto.getMccbStatus() : 0;

    }

}
