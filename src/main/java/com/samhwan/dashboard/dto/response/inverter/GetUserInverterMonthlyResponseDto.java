package com.samhwan.dashboard.dto.response.inverter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.repository.InverterMonthlyRow;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterMonthlyResponseDto extends ResponseDto {

  private final List<InverterMonthlyRow> monthly;

  private GetUserInverterMonthlyResponseDto(List<InverterMonthlyRow> monthly) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.monthly = monthly;
  }

  public static ResponseEntity<GetUserInverterMonthlyResponseDto> success(List<InverterMonthlyRow> rows) {
    return ResponseEntity.status(HttpStatus.OK).body(new GetUserInverterMonthlyResponseDto(rows));
  }

  @Getter
  @Builder
  public static class InverterMonthlyRow{

  private final Long day;
  private final Long plantId;
  private final Long invId;
  private final Double totalValue;
  private final Long samples;
    public InverterMonthlyRow( Long day,Long plantId,
      Long invId,Double totalValue, Long samples
    ) {
        this.day = day;
        this.plantId = plantId;
        this.invId = invId;
        this.totalValue = totalValue;
        this.samples = samples;
    }
  }
}
