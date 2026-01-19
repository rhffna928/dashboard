package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "alarm")
@Table(name = "alarm")
public class Alarm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", referencedColumnName = "plant_id", nullable = false)
    private PlantList2 plant;

    @Column(name = "device_type", nullable = false, length = 50)
    private String deviceType;

    @Column(name = "device_id", nullable = false, length = 50)
    private String deviceId;

    @Column(name = "device_name", nullable = false, length = 50)
    private String deviceName;

    @Column(name = "alarm_message", nullable = false, length = 50)
    private String alarmMessage;

    @Column(name = "alarm_flag", nullable = false, length = 1)
    private String alarmFlag; // 1 발생, 2 변경, 3 해제

    @Column(name = "alert_flag", nullable = false, length = 1)
    private String alertFlag; // 1 시작, 2 중지, 3 삭제

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;
}
