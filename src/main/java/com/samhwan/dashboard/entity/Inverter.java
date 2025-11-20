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
@Entity(name="inverter")
@Table(name="inverter")
public class Inverter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "inv_id", nullable = false)
    private Integer invId;

    @Column(name = "inv_status", nullable = false, length = 50)
    private String invStatus;

    @Column(name = "inv_fault", length = 50)
    private String invFault;

    @Column(name = "in_volt", nullable = false)
    private Double inVolt;

    @Column(name = "in_current", nullable = false)
    private Double inCurrent;

    @Column(name = "in_power", nullable = false)
    private Double inPower;

    @Column(name = "out_volt1", nullable = false)
    private Double outVolt1;

    @Column(name = "out_volt2", nullable = false)
    private Double outVolt2;

    @Column(name = "out_volt3", nullable = false)
    private Double outVolt3;

    @Column(name = "out_current1", nullable = false)
    private Double outCurrent1;

    @Column(name = "out_current2", nullable = false)
    private Double outCurrent2;

    @Column(name = "out_current3", nullable = false)
    private Double outCurrent3;

    @Column(name = "out_power", nullable = false)
    private Double outPower;

    @Column(name = "hz", nullable = false)
    private Double hz;

    @Column(name = "today_gen", nullable = false)
    private Double todayGen;

    @Column(name = "total_gen", nullable = false)
    private Double totalGen;

    @Column(name = "recv_time", nullable = false)
    private LocalDateTime recvTime;

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;
}
