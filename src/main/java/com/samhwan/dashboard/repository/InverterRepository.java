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
        WITH base AS (
          SELECT i.*
          FROM inverter i
          JOIN plant_list2 p ON p.plant_id = i.plant_id
          WHERE p.user_id = :userId
          AND (:plantId IS NULL OR i.plant_id = :plantId)
          AND (:invId IS NULL OR i.inv_id = :invId)
        ),
        --  전체 누적(total_gen): "인버터별 최신행"을 찾아 SUM
        last_any AS (
          SELECT b.plant_id, b.inv_id, MAX(b.regdate) AS max_regdate
          FROM base b
          GROUP BY b.plant_id, b.inv_id
        ),
        last_rows AS (
          SELECT b.*
          FROM base b
          JOIN last_any l
            ON l.plant_id = b.plant_id
          AND l.inv_id   = b.inv_id
          AND l.max_regdate = b.regdate
        ),
        --  오늘 기준 최신행(금일 발전량/현재 발전량)
        today_base AS (
          SELECT b.*
          FROM base b
          WHERE b.regdate >= CURDATE()
        ),
        today_last_any AS (
          SELECT tb.plant_id, tb.inv_id, MAX(tb.regdate) AS max_regdate
          FROM today_base tb
          GROUP BY tb.plant_id, tb.inv_id
        ),
        today_last_rows AS (
          SELECT tb.*
          FROM today_base tb
          JOIN today_last_any l
            ON l.plant_id = tb.plant_id
          AND l.inv_id   = tb.inv_id
          AND l.max_regdate = tb.regdate
        ),
        --  월간: "일자+인버터" 단위로 MAX(today_gen) → 합
        month_daily_max AS (
          SELECT DATE(b.regdate) AS d, b.plant_id, b.inv_id, MAX(b.today_gen) AS daily_max
          FROM base b
          WHERE b.regdate >= DATE_FORMAT(CURDATE(), '%Y-%m-01')
            AND b.regdate <  DATE_ADD(DATE_FORMAT(CURDATE(), '%Y-%m-01'), INTERVAL 1 MONTH)
          GROUP BY DATE(b.regdate), b.plant_id, b.inv_id
        ),
        --  전일: 인버터별 MAX(today_gen) → 합
        yesterday_per_inv AS (
          SELECT b.plant_id, b.inv_id, MAX(b.today_gen) AS y_max
          FROM base b
          WHERE b.regdate >= (CURDATE() - INTERVAL 1 DAY)
            AND b.regdate <  CURDATE()
          GROUP BY b.plant_id, b.inv_id
        ),
        --  발전시간: 오늘 out_power>0 구간의 min~max (네 기존 해석 유지)
        today_on AS (
          SELECT MIN(tb.regdate) AS min_reg, MAX(tb.regdate) AS max_reg
          FROM today_base tb
          WHERE tb.out_power > 0
        )
        SELECT
          /* 1) 발전 시간(시간) */
          COALESCE(
            ROUND(
              TIMESTAMPDIFF(
                MINUTE,
                (SELECT min_reg FROM today_on),
                (SELECT max_reg FROM today_on)
              ) / 60, 2
            ),
            0
          ) AS gen_hours,
          /* 2) 누적 발전량(kWh): 인버터별 최신행 total_gen 합 */
          COALESCE((SELECT ROUND(SUM(lr.total_gen), 1) FROM last_rows lr), 0) AS total_gen_kwh,
          /* 3) 월간 생산량(kWh): (일자+인버터)별 MAX(today_gen) 합 */
          COALESCE((SELECT ROUND(SUM(m.daily_max), 1) FROM month_daily_max m), 0) AS month_gen_kwh,
          /* 4) 전일 발전량(kWh): 인버터별 MAX(today_gen) 합 */
          COALESCE((SELECT ROUND(SUM(y.y_max), 1) FROM yesterday_per_inv y), 0) AS yesterday_gen_kwh,
          /* 5) 금일 발전량(kWh): 오늘 인버터별 최신행 today_gen 합 */
          COALESCE((SELECT ROUND(SUM(tlr.today_gen), 1) FROM today_last_rows tlr), 0) AS today_gen_kwh,
          /* 6) 현재 발전량(kW): 오늘 인버터별 최신행 out_power 합 */
          COALESCE((SELECT ROUND(SUM(tlr.out_power), 1) FROM today_last_rows tlr), 0) AS current_power_kw
        ;
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
