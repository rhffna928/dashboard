package com.samhwan.dashboard.dto.response.plant_list;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.PlantList2;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserPlantList2ResponseDto extends ResponseDto{


    
    private final List<PlantSummary> plantList2;

    private GetUserPlantList2ResponseDto(List<PlantSummary> plantList2) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.plantList2 = plantList2;
    }

    public static ResponseEntity<GetUserPlantList2ResponseDto> success(List<PlantList2> plantList2){
        List<PlantSummary> list = plantList2.stream()
                                    .map(PlantSummary::fromEntity)
                                    .toList();
        GetUserPlantList2ResponseDto result =  new GetUserPlantList2ResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    @Getter
    @Builder
    public static class PlantSummary {

        // ✅ plant_list2
        private Integer plantId;         // plant_id
        private Integer plantCode;       // plant_code
        private String plantName;        // plant_name
        private String plantOwner;       // plant_owner
        private String plantMan;         // plant_man
        private String userId;           // user_id

        private String plantCapacity;    // plant_capacity (varchar)
        private String plantPrice;       // plant_price (varchar)
        private String address;          // address
        private String lat;              // lat
        private String lng;              // lng

        private Integer invCount;        // inv_count
        private Integer invCountDisable; // inv_count_disable

        private String useYn;            // useYN
        private String smsYn;            // smsYN
        private String infoYn;           // infoYN

        private String startYmd;         // startYmd
        private String startYear;        // startYear

        private String moduleInfo;       // module_info
        private String invInfo;          // inv_info
        private Integer getDataSec;      // getDataSec

        private Double yesGen;           // yes_gen
        private Double monthGen;         // month_gen

        private LocalDateTime regdate;   // regdate

        public static PlantSummary fromEntity(PlantList2 pl) {
            return PlantSummary.builder()
                    .plantId(pl.getPlantId())
                    .plantCode(pl.getPlantCode())
                    .plantName(pl.getPlantName())
                    .plantOwner(pl.getPlantOwner())
                    .plantMan(pl.getPlantMan())
                    .userId(pl.getUserId())

                    .plantCapacity(pl.getPlantCapacity())
                    .plantPrice(pl.getPlantPrice())
                    .address(pl.getAddress())
                    .lat(pl.getLat())
                    .lng(pl.getLng())

                    .invCount(pl.getInvCount())
                    .invCountDisable(pl.getInvCountDisable())
                    .useYn(pl.getUseYn())
                    .smsYn(pl.getSmsYn())
                    .infoYn(pl.getInfoYn())
                    .startYmd(pl.getStartYmd())
                    .startYear(pl.getStartYear())
                    .moduleInfo(pl.getModuleInfo())
                    .invInfo(pl.getInvInfo())
                    .getDataSec(pl.getGetDataSec())
                    .yesGen(pl.getYesGen())
                    .monthGen(pl.getMonthGen())
                    .regdate(pl.getRegdate())
                    .build();
        }
    }

}
