package com.samhwan.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.InverterList;

@Repository
public interface InverterListRepository extends JpaRepository<InverterList, Integer> {

}
