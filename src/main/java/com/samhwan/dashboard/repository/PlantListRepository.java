package com.samhwan.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.PlantList;

@Repository
public interface PlantListRepository extends JpaRepository<PlantList, Integer> {

}
