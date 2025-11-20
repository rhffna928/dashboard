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
@Table
public class Plant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "op_status", nullable = false, length = 50)
    private String opStatus;

    @Column(name = "op_alarm", length = 200)
    private String opAlarm;

    @Column(name = "inv_cnt_total", nullable = false)
    private Integer invCntTotal;

    @Column(name = "inv_cnt_run", nullable = false)
    private Integer invCntRun;

    @Column(name = "inv_cnt_stop", nullable = false)
    private Integer invCntStop;

    @Column(name = "inv_cnt_timeout", nullable = false)
    private Integer invCntTimeout;

    @Column(name = "inv_cnt_disable", nullable = false)
    private Integer invCntDisable;

    @Column(name = "out_power", nullable = false)
    private Double outPower;

    @Column(name = "today_gen", nullable = false)
    private Double todayGen;

    @Column(name = "total_gen", nullable = false)
    private Double totalGen;

    @Column(name = "recv_time", nullable = false)
    private LocalDateTime recvTime;

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;

}
