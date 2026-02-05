package com.samhwan.dashboard.dto.response.inverter_list;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.InverterList2;

import lombok.Getter;

@Getter
public class GetUserInverterList2ResponseDto extends ResponseDto{

    
    private final List<InverterSummary> inverters;

    private GetUserInverterList2ResponseDto(List<InverterSummary> inverters) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.inverters = inverters;
    }
    public static ResponseEntity<GetUserInverterList2ResponseDto> success(List<InverterList2> inverters){
        List<InverterSummary> list = inverters.stream()
                                    .map(InverterSummary::fromEntity)
                                    .toList();
        GetUserInverterList2ResponseDto result =  new GetUserInverterList2ResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Getter
    public static class InverterSummary {
        private int id;
        private int plantId;
        private int groupId;
        private int unitId;
        private String invId;
        private String invName;
        private String invType;
        private String invModel;
        private String invProtocol;
        private Double invCapacity;
        private Double minPower;
        private Double maxPower;
        private Double todayGen;
        private Double totalGen;
        private String useYn;
        private String invFault;
        private int mccbId;
        private int mccbStatus;



        public static InverterSummary fromEntity(InverterList2 inverters) {
            InverterSummary s = new InverterSummary();
            s.id = inverters.getId();
            s.plantId = inverters.getPlantId();
            s.groupId = inverters.getGroupId();
            s.unitId = inverters.getUnitId();

            s.invId = inverters.getInvId();
            s.invName = inverters.getInvName();
            s.invType = inverters.getInvType();
            s.invModel = inverters.getInvModel();
            s.invProtocol = inverters.getInvProtocol();

            s.invCapacity = inverters.getInvCapacity();
            s.minPower = inverters.getMinPower();
            s.maxPower = inverters.getMaxPower();
            s.todayGen = inverters.getTodayGen();
            s.totalGen = inverters.getTotalGen();

            s.useYn = inverters.getUseYn();
            s.invFault = inverters.getInvFault();

            s.mccbId = inverters.getMccbId();
            s.mccbStatus = inverters.getMccbStatus();
            return s;
        }
    }

}
