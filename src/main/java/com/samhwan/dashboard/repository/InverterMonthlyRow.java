package com.samhwan.dashboard.repository;

public interface InverterMonthlyRow {

    Long getDay();
    Long getPlantId();
    Long getInvId();
    Double getTotalValue();
    Long getSamples();
}
