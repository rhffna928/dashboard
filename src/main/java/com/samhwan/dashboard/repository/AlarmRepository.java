package com.samhwan.dashboard.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.dto.response.alarm.GetAlarmListResponseDto;
import com.samhwan.dashboard.entity.Alarm;


@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
    
    @Query(
        value = """
            SELECT a
            FROM Alarm a
            JOIN PlantList2 p ON p.plantId = a.plantId
            WHERE p.userId = :userId
              AND (:plantId IS NULL OR a.plantId = :plantId)
              AND a.regdate >= :fromDt
              AND a.regdate <  :toExclusive
              AND (:deviceType = 'ALL' OR a.deviceType = :deviceType)
              AND (:deviceId   = 'ALL' OR a.deviceId   = :deviceId)
              AND a.alertFlag <> '3'
            ORDER BY a.regdate DESC
            """,
        countQuery = """
            SELECT COUNT(a)
            FROM Alarm a
            JOIN PlantList2 p ON p.plantId = a.plantId
            WHERE p.userId = :userId
              AND (:plantId IS NULL OR a.plantId = :plantId)
              AND a.regdate >= :fromDt
              AND a.regdate <  :toExclusive
              AND (:deviceType = 'ALL' OR a.deviceType = :deviceType)
              AND (:deviceId   = 'ALL' OR a.deviceId   = :deviceId)
              AND a.alertFlag <> '3'
            """
    )
    Page<Alarm> findAlarmList(
        @Param("userId") String userId,
        @Param("plantId") Integer plantId,
        @Param("fromDt") LocalDateTime fromDt,
        @Param("toExclusive") LocalDateTime toExclusive,
        @Param("deviceType") String deviceType,
        @Param("deviceId") String deviceId,
        Pageable pageable
    );

    @Query("""
        SELECT DISTINCT a.deviceId
        FROM Alarm a
        JOIN PlantList2 p ON p.plantId = a.plantId
        WHERE p.userId = :userId
          AND (:plantId IS NULL OR a.plantId = :plantId)
          AND a.regdate >= :fromDt
          AND a.regdate <  :toExclusive
          AND (:deviceType = 'ALL' OR a.deviceType = :deviceType)
          AND a.alertFlag <> '3'
        ORDER BY a.deviceId ASC
    """)
    List<String> findDeviceIdOptions(
        @Param("userId") String userId,
        @Param("plantId") Integer plantId,
        @Param("fromDt") LocalDateTime fromDt,
        @Param("toExclusive") LocalDateTime toExclusive,
        @Param("deviceType") String deviceType
    );
  
}