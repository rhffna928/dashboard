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
@Entity(name="SwInverter")
@Table(name="SwInverter")
public class SwInverter {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "inv_id", nullable = false)
    private Integer invId;

    @Column(name = "total_gen", nullable = false)
    private Double totalGen;

    @Column(name = "today_gen", nullable = false, length = 20)
    private String todayGen;

    @Column(name = "factor", nullable = false, length = 20)
    private String factor;

    @Column(name = "max_power", nullable = false, length = 20)
    private String maxPower;

    @Column(name = "sys_power", nullable = false, length = 20)
    private String sysPower;

    @Column(name = "sys_volt", nullable = false, length = 20)
    private String sysVolt;

    @Column(name = "sys_current", nullable = false, length = 20)
    private String sysCurrent;

    @Column(name = "hz", nullable = false, length = 20)
    private String hz;

    @Column(name = "cell_power", nullable = false, length = 20)
    private String cellPower;

    @Column(name = "recv_at")
    private LocalDateTime recvAt;
}
