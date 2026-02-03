package com.samhwan.dashboard.repository;

import com.samhwan.dashboard.dto.response.inverter.GetUserHeaderResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.entity.InverterList2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    @Query(
      value = """
        SELECT *
        FROM (
          SELECT
            i.*,
            p.plant_name as plantName,
            FROM_UNIXTIME(
              FLOOR(UNIX_TIMESTAMP(i.regdate) / (:bucketSec * 60)) * (:bucketSec * 60)
            ) AS bucketTime,
            ROW_NUMBER() OVER (
              PARTITION BY
                i.plant_id,
                i.inv_id,
                FLOOR(UNIX_TIMESTAMP(i.regdate) / (:bucketSec * 60))
              ORDER BY i.regdate DESC
            ) AS rn
          FROM inverter i
          
          JOIN plant_list2 p ON p.plant_id = i.plant_id
          JOIN inverter_list2 il
            ON il.inv_id = i.inv_id
          WHERE p.user_id = :userId
            AND (:plantId IS NULL OR i.plant_id = :plantId)
            AND (:invId IS NULL OR i.inv_id = :invId)
            AND i.regdate >= :fromDt
            AND i.regdate <  :toExclusive
        ) t
        WHERE t.rn = 1
        ORDER BY t.regdate DESC
      """,
      countQuery = """
        SELECT COUNT(*)
        FROM (
          SELECT DISTINCT
            i.plant_id,
            i.inv_id,
            FLOOR(UNIX_TIMESTAMP(i.regdate) / (:bucketSec * 60)) AS bucket_key
          FROM inverter i
          JOIN plant_list2 p ON p.plant_id = i.plant_id
          WHERE p.user_id = :userId
            AND (:plantId IS NULL OR i.plant_id = :plantId)
            AND (:invId IS NULL OR i.inv_id = :invId)
            AND i.regdate >= :fromDt
            AND i.regdate <  :toExclusive
        ) x
      """,
      nativeQuery = true
    )
    Page<InverterHistoryView> findInverterHistory(
        @Param("userId") String userId,
        @Param("plantId") Integer plantId,
        @Param("invId") Integer invId,
        @Param("fromDt") LocalDateTime fromDt,
        @Param("toExclusive") LocalDateTime toExclusive,
        @Param("bucketSec") Integer bucketSec,
        Pageable pageable
    );

    @Query(value = 
      """
      SELECT
        /* 1) 발전 시간(시간): 오늘 out_power>0 인 구간의 최초~최종 regdate 차이 */
        COALESCE((
          SELECT ROUND(TIMESTAMPDIFF(MINUTE, MIN(i.regdate), MAX(i.regdate)) / 60, 2)
          FROM inverter i
          JOIN plant_list2 p ON p.plant_id = i.plant_id
          WHERE p.user_id = :userId
            and (:plantId is null or i.plant_id = :plantId)
            and (:invId is null or i.inv_id = :invId)
            AND i.regdate >= CURDATE()
            AND i.out_power > 0
        ), 0) AS gen_hours,
        /* 2) 누적 발전량(kWh): 해당 inv 최신행 total_gen */
        COALESCE((
          SELECT ROUND(li.total_gen, 1)
          FROM inverter li
          JOIN plant_list2 p ON p.plant_id = li.plant_id
          WHERE p.user_id = :userId
          and (:plantId is null or li.plant_id = :plantId)
          and (:invId is null or li.inv_id = :invId)
          ORDER BY li.regdate DESC
          LIMIT 1
        ), 0) AS total_gen_kwh,
        /* 3) 월간 생산량(kWh): 이번달(일자별) max(today_gen) 합 */
        COALESCE((
          SELECT ROUND(SUM(d.daily_max), 1)
          FROM (
            SELECT DATE(i.regdate) AS d, MAX(i.today_gen) AS daily_max
            FROM inverter i
            JOIN plant_list2 p ON p.plant_id = i.plant_id
            WHERE p.user_id = :userId
              and (:plantId is null or i.plant_id = :plantId)
              and (:invId is null or i.inv_id = :invId)
              AND i.regdate >= DATE_FORMAT(CURDATE(), '%Y-%m-01')
              AND i.regdate <  DATE_ADD(DATE_FORMAT(CURDATE(), '%Y-%m-01'), INTERVAL 1 MONTH)
            GROUP BY DATE(i.regdate)
          ) d
        ), 0) AS month_gen_kwh,
        /* 4) 전일 발전량(kWh): 어제 max(today_gen) */
        COALESCE((
          SELECT ROUND(MAX(i.today_gen), 1)
          FROM inverter i
          JOIN plant_list2 p ON p.plant_id = i.plant_id
          WHERE p.user_id = :userId
          and (:plantId is null or i.plant_id = :plantId)
          and (:invId is null or i.inv_id = :invId)
            AND i.regdate >= (CURDATE() - INTERVAL 1 DAY)
            AND i.regdate <  CURDATE()
        ), 0) AS yesterday_gen_kwh,
        /* 5) 금일 발전량(kWh): 최신행 today_gen */
        COALESCE((
          SELECT ROUND(li.today_gen, 1)
          FROM inverter li
          JOIN plant_list2 p ON p.plant_id = li.plant_id
          WHERE p.user_id = :userId
          and (:plantId is null or li.plant_id = :plantId)
          and (:invId is null or li.inv_id = :invId)
          AND li.regdate >= CURDATE()
          ORDER BY li.regdate DESC
          LIMIT 1
        ), 0) AS today_gen_kwh,
        /* 6) 현재 발전량(kW): 최신행 out_power */
        COALESCE((
          SELECT ROUND(li.out_power, 1)
          FROM inverter li
          JOIN plant_list2 p ON p.plant_id = li.plant_id
          WHERE p.user_id = :userId
          and (:plantId is null or li.plant_id = :plantId)
          and (:invId is null or li.inv_id = :invId)
          AND li.regdate >= CURDATE()
          ORDER BY li.regdate DESC
          LIMIT 1
        ), 0) AS current_power_kw;
      """, nativeQuery = true)
    Optional<DashboardKpiView> findLatestByUserIdAndInvId(
        @Param("userId") String userId,
        @Param("invId") Integer invId,
        @Param("plantId") Integer plantId

    );


    @Query(value = """
        SELECT COALESCE(SUM(i.out_power), 0) AS currentPowerKw
        FROM inverter i
        JOIN (
          SELECT i2.plant_id, i2.inv_id, MAX(i2.regdate) AS max_regdate
          FROM inverter i2
          JOIN plant_list2 p2 ON p2.plant_id = i2.plant_id
          WHERE p2.user_id = :userId
            AND i2.regdate >= NOW() - INTERVAL 10 MINUTE
          GROUP BY i2.plant_id, i2.inv_id
        ) t
          ON t.plant_id = i.plant_id
        AND t.inv_id   = i.inv_id
        AND t.max_regdate = i.regdate;
        """, nativeQuery = true)    
    GetUserHeaderResponseDto.InverterHeader getCurrentPower(@Param("userId") String userId);




}
