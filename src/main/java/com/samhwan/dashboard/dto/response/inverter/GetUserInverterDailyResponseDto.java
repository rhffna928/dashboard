package com.samhwan.dashboard.dto.response.inverter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.repository.InverterDailyRow;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterDailyResponseDto extends ResponseDto {

  private final List<InverterDailyRow> day;

  private GetUserInverterDailyResponseDto(List<InverterDailyRow> day) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.day = day;
  }

  public static ResponseEntity<GetUserInverterDailyResponseDto> success(List<InverterDailyRow> day) {
    return ResponseEntity.status(HttpStatus.OK).body(new GetUserInverterDailyResponseDto(day));
  }

  @Getter
  @Builder
  public static class InverterDailyRow{
 
  private final Long hour;
  private final Long plantId;
  private final Long invId;
  private final Double totalValue;
  private final Long samples;
    public InverterDailyRow( Long hour,Long plantId,
      Long invId,Double totalValue, Long samples
    ) {
        this.hour = hour;
        this.plantId = plantId;
        this.invId = invId;
        this.totalValue = totalValue;
        this.samples = samples;
    }
  }


}
