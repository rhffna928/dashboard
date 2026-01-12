package com.samhwan.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.InverterList2;

@Repository
public interface InverterList2Repository extends JpaRepository<InverterList2, Integer> {


    List<InverterList2> findAllByOrderByIdAsc();

    boolean existsByInvName(String invName);
    
}
