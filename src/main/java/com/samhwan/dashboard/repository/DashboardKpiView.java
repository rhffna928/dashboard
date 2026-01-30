package com.samhwan.dashboard.repository;

public interface DashboardKpiView {
    
    Double getGenHours();
    Double getTotalGenKwh();
    Double getMonthGenKwh();
    Double getYesterdayGenKwh();
    Double getTodayGenKwh();
    Double getCurrentPowerKw();

}
