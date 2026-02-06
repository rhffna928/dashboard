package com.samhwan.dashboard.repository;

public interface InverterSeriesRow {
    String getBucketHour();
    Long getPlantId();
    Long getInvId();
    Double getHourGenKwh();
    Long getSamples();
}
