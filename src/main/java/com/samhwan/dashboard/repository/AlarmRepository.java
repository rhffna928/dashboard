package com.samhwan.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.Alarm;
import com.samhwan.dashboard.entity.User2;


@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
    
  @Query("""
    select new com.samhwan.dashboard.dto.AlarmViewDto(
      a.id,
      p.plantId,
      p.plantName,
      a.deviceType,
      a.deviceId,
      a.deviceName,
      a.alarmMessage,
      a.alarmFlag,
      a.alertFlag,
      a.regdate
    )
    from Alarm a
    join a.plant p
    join p.user u
    where u.userId = :userId
    order by a.regdate desc
  """)
  Page<GetAlarmListResponseDto> findRecentAlarmsForUser(@Param("userId") String userId, Pageable pageable);

    
}