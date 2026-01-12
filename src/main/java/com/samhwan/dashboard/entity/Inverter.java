package com.samhwan.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inverter")
public class Inverter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "inv_id", nullable = false)
    private Integer invId;

    @Column(name = "inv_status", length = 50)
    private String invStatus;

    @Column(name = "inv_fault", length = 50)
    private String invFault;

    @Column(name = "in_volt")
    private Double inVolt;

    @Column(name = "in_current")
    private Double inCurrent;

    @Column(name = "in_power")
    private Double inPower;

    @Column(name = "out_volt1")
    private Double outVolt1;

    @Column(name = "out_volt2")
    private Double outVolt2;

    @Column(name = "out_volt3")
    private Double outVolt3;

    @Column(name = "out_current1")
    private Double outCurrent1;

    @Column(name = "out_current2")
    private Double outCurrent2;

    @Column(name = "out_current3")
    private Double outCurrent3;

    @Column(name = "out_power")
    private Double outPower;

    @Column(name = "hz")
    private Double hz;

    @Column(name = "today_gen")
    private Double todayGen;

    @Column(name = "total_gen")
    private Double totalGen;

    @Column(name = "recv_time")
    private LocalDateTime recvTime;

    @Column(name = "regdate")
    private LocalDateTime regdate;
}
