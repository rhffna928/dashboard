package com.samhwan.dashboard.repository;

import com.samhwan.dashboard.entity.PlantList2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlantList2Repository extends JpaRepository<PlantList2, Integer> {


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update PlantList2 p set p.invCount = p.invCount + 1 where p.plantId = :plantId")
    int incInvCountUp(@Param("plantId") Integer plantId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update PlantList2 p set p.invCount = p.invCount - 1 where p.plantId = :plantId and p.invCount > 0")
    int incInvCountDown(@Param("plantId") Integer plantId);

    List<PlantList2> findAllByUserId(String userId);

    boolean existsByPlantName(String plantName);
}
