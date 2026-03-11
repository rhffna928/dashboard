package com.samhwan.dashboard.repository;

public interface InverterYearlyRow {
    Long getMonth();
    Long getPlantId();
    Long getInvId();
    Double getTotalValue();
    Long getSamples();
}
