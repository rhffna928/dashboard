package com.samhwan.dashboard.repository;

public interface InverterView {
    
    String getBucketHour();
    Integer getPlantId();
    Integer getInvId();
    Double getHourGenKwh();
    Integer getSamples();

}
