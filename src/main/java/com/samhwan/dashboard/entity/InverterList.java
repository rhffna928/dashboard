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
@Entity(name="inverter_list")
@Table(name="inverter_list")
public class InverterList {

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

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;
}
