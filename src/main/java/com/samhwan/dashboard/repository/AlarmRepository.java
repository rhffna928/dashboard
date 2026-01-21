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
    
  // deviceId 옵션 목록
  @Query(value = """
    select distinct a.device_id
    from alarm a
    join plant_list2 p on p.plant_id = a.plant_id
    where p.user_id = :userId
      and (:plantId is null or a.plant_id = :plantId)
      and a.regdate >= :from and a.regdate < :to
      and (:deviceType = 'ALL' or a.device_type = :deviceType)
    order by a.device_id
  """, nativeQuery = true)
  List<String> findDeviceIdOptions(
      @Param("userId") String userId,
      @Param("plantId") Integer plantId,
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to,
      @Param("deviceType") String deviceType
  );

  // 알람 목록 (페이지)
  @Query(
    value = """
      select a.*
      from alarm a
      join plant_list2 p on p.plant_id = a.plant_id
      where p.user_id = :userId
        and (:plantId is null or a.plant_id = :plantId)
        and a.regdate >= :from and a.regdate < :to
        and (:deviceType = 'ALL' or a.device_type = :deviceType)
        and (:deviceId = 'ALL' or a.device_id = :deviceId)
      order by a.regdate desc
    """,
    countQuery = """
      select count(*)
      from alarm a
      join plant_list2 p on p.plant_id = a.plant_id
      where p.user_id = :userId
        and (:plantId is null or a.plant_id = :plantId)
        and a.regdate >= :from and a.regdate < :to
        and (:deviceType = 'ALL' or a.device_type = :deviceType)
        and (:deviceId = 'ALL' or a.device_id = :deviceId)
    """,
    nativeQuery = true
  )
  Page<Alarm> findAlarmList(
      @Param("userId") String userId,
      @Param("plantId") Integer plantId,
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to,
      @Param("deviceType") String deviceType,
      @Param("deviceId") String deviceId,
      Pageable pageable
  );
  
}