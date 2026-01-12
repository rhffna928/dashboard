package com.samhwan.dashboard.repository;

import com.samhwan.dashboard.entity.Inverter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface InverterRepository extends JpaRepository<Inverter, Integer> {

    // 특정 발전소(plantId)의 인버터 목록(최신순)
    List<Inverter> findByPlantIdOrderByRecvTimeDesc(Integer plantId);

    // 특정 발전소+인버터(invId)의 기간별 데이터 (그래프용)
    List<Inverter> findByPlantIdAndInvIdAndRecvTimeBetweenOrderByRecvTimeAsc(
            Integer plantId, Integer invId, LocalDateTime start, LocalDateTime end
    );
    // 특정 발전소+인버터 최신 데이터 N개
    List<Inverter> findByPlantIdAndInvIdOrderByRecvTimeDesc(Integer plantId, Integer invId, Pageable pageable);

}
