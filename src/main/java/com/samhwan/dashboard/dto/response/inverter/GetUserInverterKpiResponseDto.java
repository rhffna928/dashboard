package com.samhwan.dashboard.dto.response.inverter;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterKpiResponseDto extends ResponseDto{


    private final DashboardKpi kpi;

    private GetUserInverterKpiResponseDto(DashboardKpi kpi) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.kpi = kpi;
        
    }
    public static ResponseEntity<GetUserInverterKpiResponseDto> success(DashboardKpi kpi){
        
        GetUserInverterKpiResponseDto result =  new GetUserInverterKpiResponseDto(kpi);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

  @Getter
  @Builder
  public static class DashboardKpi {
    private final Double genHours;
    private final Double totalGenKwh;
    private final Double monthGenKwh;
    private final Double yesterdayGenKwh;
    private final Double todayGenKwh;
    private final Double currentPowerKw;

    public DashboardKpi(Double genHours, Double totalGenKwh, Double monthGenKwh,
                        Double yesterdayGenKwh, Double todayGenKwh, Double currentPowerKw) {
      this.genHours = genHours;
      this.totalGenKwh = totalGenKwh;
      this.monthGenKwh = monthGenKwh;
      this.yesterdayGenKwh = yesterdayGenKwh;
      this.todayGenKwh = todayGenKwh;
      this.currentPowerKw = currentPowerKw;
    }
  }

}
