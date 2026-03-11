package com.samhwan.dashboard.repository;

public interface InverterDailyRow {
    
    Long getHour();
    Long getPlantId();
    Long getInvId();
    Double getTotalValue();
    Long getSamples();
}
