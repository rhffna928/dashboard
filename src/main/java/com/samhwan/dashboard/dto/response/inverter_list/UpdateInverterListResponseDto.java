package com.samhwan.dashboard.dto.response.inverter_list;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.InverterList2;
import lombok.Getter;

@Getter
public class UpdateInverterListResponseDto extends ResponseDto {
    
    private Integer plantId;     // 발전소 아이디
    private Integer groupId;     // 그룹 아이디
    private Integer unitId;      // 국번
    private String invId;
    private String invName;
    private String invType;
    private String invModel;
    private String invProtocol;
    private Double invCapacity; // double(22,1)
    private Double minPower;
    private Double maxPower;
    private Double todayGen;
    private Double totalGen;
    private String useYn;
    private String invFault;
    private Integer mccbId;
    private Integer mccbStatus;

    private UpdateInverterListResponseDto(InverterList2 inverterList2){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.plantId = inverterList2.getPlantId();
        this.groupId = inverterList2.getGroupId();
        this.unitId = inverterList2.getUnitId();
        this.invName = inverterList2.getInvName();
        this.invType = inverterList2.getInvType();
        this.invModel = inverterList2.getInvModel();
        this.invProtocol = inverterList2.getInvProtocol();
        this.invCapacity = inverterList2.getInvCapacity();
        this.minPower = inverterList2.getMinPower();
        this.maxPower = inverterList2.getMaxPower();
        this.todayGen = inverterList2.getTodayGen();
        this.totalGen = inverterList2.getTotalGen();
        this.useYn = inverterList2.getUseYn();
        this.invFault = inverterList2.getInvFault();
        this.mccbId = inverterList2.getMccbId();
        this.mccbStatus = inverterList2.getMccbStatus();
    }

    public static ResponseEntity<UpdateInverterListResponseDto> success(InverterList2 inverterList2){
        UpdateInverterListResponseDto result =  new UpdateInverterListResponseDto(inverterList2);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    public static ResponseEntity<ResponseDto> permit(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
