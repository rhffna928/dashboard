package com.samhwan.dashboard.dto.response.inverter;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.inverter.GetUserInverterLatestListResponseDto.InverterLatestRow;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.InverterHistoryView;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterLatestListResponseDto extends ResponseDto{


    private final List<InverterLatestRow> inverters;

    private GetUserInverterLatestListResponseDto(List<InverterLatestRow> inverters) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.inverters = inverters;
        
    }
    public static ResponseEntity<GetUserInverterLatestListResponseDto> success(List<InverterLatestRow> inverters){
        
        GetUserInverterLatestListResponseDto result =  new GetUserInverterLatestListResponseDto(inverters);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Getter
    @Builder
    public static class InverterLatestRow {
        private Integer id;
        private Integer plantId;
        private Integer invId;

        private String invStatus;
        private String invFault;

        private Double inVolt;
        private Double inCurrent;
        private Double inPower;

        private Double outVolt1;
        private Double outVolt2;
        private Double outVolt3;

        private Double outCurrent1;
        private Double outCurrent2;
        private Double outCurrent3;

        private Double outPower;
        private Double hz;

        private Double todayGen;
        private Double totalGen;

        private LocalDateTime recvTime;
        private LocalDateTime regdate;

        public static InverterLatestRow fromEntity(Inverter e) {
            return InverterLatestRow.builder()
                .id(e.getId())
                .plantId(e.getPlantId())
                .invId(e.getInvId())
                .invStatus(e.getInvStatus())
                .invFault(e.getInvFault())
                .inVolt(e.getInVolt())
                .inCurrent(e.getInCurrent())
                .inPower(e.getInPower())
                .outVolt1(e.getOutVolt1())
                .outVolt2(e.getOutVolt2())
                .outVolt3(e.getOutVolt3())
                .outCurrent1(e.getOutCurrent1())
                .outCurrent2(e.getOutCurrent2())
                .outCurrent3(e.getOutCurrent3())
                .outPower(e.getOutPower())
                .hz(e.getHz())
                .todayGen(e.getTodayGen())
                .totalGen(e.getTotalGen())
                .recvTime(e.getRecvTime())
                .regdate(e.getRegdate())
                .build();
        }
    }
}
