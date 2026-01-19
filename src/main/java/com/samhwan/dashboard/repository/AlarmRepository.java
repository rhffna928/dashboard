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
    
  @Query("""
    select a
    from Alarm a
    join a.plant p
    join p.user u
    where u.userId = :userId
      and (:plantId is null or p.plantId = :plantId)
      and a.regdate >= :fromDt
      and a.regdate < :toExclusive
      and (:dt = 'ALL' or a.deviceType = :dt)
      and (:di = 'ALL' or a.deviceId = :di)
      and a.alertFlag <> '3'
    order by a.regdate desc
  """)
  Page<Alarm> findAlarmList(
      @Param("userId") String userId,
      @Param("plantId") Integer plantId,
      @Param("fromDt") LocalDateTime fromDt,
      @Param("toExclusive") LocalDateTime toExclusive,
      @Param("dt") String dt,
      @Param("di") String di,
      Pageable pageable
  );

  @Query("""
    select distinct a.deviceId
    from Alarm a
    join a.plant p
    join p.user u
    where u.userId = :userId
      and (:plantId is null or p.plantId = :plantId)
      and a.regdate >= :fromDt
      and a.regdate < :toExclusive
      and (:dt = 'ALL' or a.deviceType = :dt)
      and a.alertFlag <> '3'
    order by a.deviceId asc
  """)
  List<String> findDeviceIdOptions(
    String userId,
    Integer plantId,
    LocalDateTime fromDt,
    LocalDateTime toExclusive,
    String dt
  );

    
}