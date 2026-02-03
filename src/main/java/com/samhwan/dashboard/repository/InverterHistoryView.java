package com.samhwan.dashboard.repository;

import java.time.LocalDateTime;


public interface InverterHistoryView  {
        
    Integer getId();
    Integer getPlantId();
    Integer getInvId();
    String getInvStatus();
    String getInvFault();
    Double getInVolt();
    Double getInCurrent();
    Double getInPower();
    Double getOutVolt1();
    Double getOutVolt2();
    Double getOutVolt3();
    Double getOutCurrent1();
    Double getOutCurrent2();
    Double getOutCurrent3();
    Double getOutPower();
    Double getHz();
    Double getTodayGen();
    Double getTotalGen();
    LocalDateTime getRegdate();
    LocalDateTime getRecvTime();
    LocalDateTime getBucketTime();
    String getPlantName();

}

