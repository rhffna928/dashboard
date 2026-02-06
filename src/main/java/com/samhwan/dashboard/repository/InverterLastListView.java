package com.samhwan.dashboard.repository;

import java.time.LocalDateTime;

public interface InverterLastListView {
         Integer id();
         Integer plantId();
         Integer invId();

         String invStatus();
         String invFault();

         Double inVolt();
         Double inCurrent();
         Double inPower();

         Double outVolt1();
         Double outVolt2();
         Double outVolt3();

         Double outCurrent1();
         Double outCurrent2();
         Double outCurrent3();

         Double outPower();
         Double hz();

         Double todayGen();
         Double totalGen();

         LocalDateTime recvTime();
         LocalDateTime regdate();

}
