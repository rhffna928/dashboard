package com.samhwan.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.InverterList2;

@Repository
public interface InverterList2Repository extends JpaRepository<InverterList2, Integer> {


    List<InverterList2> findAllByOrderByIdAsc();

    boolean existsByInvName(String invName);
    
    InverterList2 findByUnitId(Integer id);

    @Query(value ="""
        SELECT il.*
        from inverter_list2 as il
        join plant_list2 as pl ON pl.plant_id = il.plant_id
        where pl.user_id = :userId
        """,nativeQuery = true)
    List<InverterList2> findAllByUserId(@Param("userId") String userId);
}
