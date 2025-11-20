package com.samhwan.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.TmpReport;

@Repository
public interface TmpReportRepository extends JpaRepository<TmpReport, Integer>{

}
