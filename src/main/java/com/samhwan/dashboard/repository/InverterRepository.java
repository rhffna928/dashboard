package com.samhwan.dashboard.repository;

import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.entity.InverterList2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

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


    @Query(value = """
      SELECT *
      FROM (
        SELECT
          i.*,  -- ✅ 콤마 필수
          FROM_UNIXTIME(FLOOR(UNIX_TIMESTAMP(i.regdate) / :iv) * :iv) AS bucket_time,
          ROW_NUMBER() OVER (
            PARTITION BY i.plant_id, i.inv_id, FLOOR(UNIX_TIMESTAMP(i.regdate) / :iv)
            ORDER BY i.regdate DESC
          ) AS rn
        FROM inverter i
        JOIN plant_list2 p ON p.plant_id = i.plant_id
        WHERE p.user_id = :userId
          AND (:invId IS NULL OR i.inv_id = :invId)     -- ✅ invId가 숫자라는 가정
          AND i.regdate >= :fromDt
          AND i.regdate <  :toExclusive
      ) t
      WHERE t.rn = 1
      ORDER BY t.regdate DESC
        """,
        countQuery = """
          SELECT COUNT(*)
          FROM (
            SELECT 1
            FROM inverter i
            JOIN plant_list2 p ON p.plant_id = i.plant_id
            WHERE p.user_id = :userId
              AND (:invId IS NULL OR i.inv_id = :invId)
              AND i.regdate >= :fromDt
              AND i.regdate <  :toExclusive
            GROUP BY i.plant_id, i.inv_id, FLOOR(UNIX_TIMESTAMP(i.regdate) / :iv)
          ) t
        """,
        nativeQuery = true)
    Page<Inverter> findInverterHistory(
        @Param("userId") String userId,
        @Param("invId") Integer invId,
        @Param("fromDt") LocalDateTime fromDt,
        @Param("toExclusive") LocalDateTime toExclusive,
        @Param("iv") Integer intervalMinutes,
        Pageable pageable
    );

    @Query(value ="""
        SELECT il.*
        from inverter_list2 as il
        join plant_list2 as pl ON pl.plant_id = il.plant_id
        where pl.user_id = :userId
        """,nativeQuery = true)
    List<InverterList2> findAllByUserId(@Param("userId") String userId);



}
