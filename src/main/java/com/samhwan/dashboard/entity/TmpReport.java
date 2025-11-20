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
@Table(name="tmp_report")
public class TmpReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plant_id", nullable = false)
    private Integer plantId;

    @Column(name = "tmp1", nullable = false)
    private Double tmp1;

    @Column(name = "tmp2", nullable = false)
    private Double tmp2;

    @Column(name = "tmp3", nullable = false)
    private Double tmp3;

    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;

    @Column(name = "recv_at", nullable = false)
    private LocalDateTime recvAt;
}
