package com.samhwan.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer>{

}
