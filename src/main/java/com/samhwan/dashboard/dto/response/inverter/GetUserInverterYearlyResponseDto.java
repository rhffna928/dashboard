package com.samhwan.dashboard.dto.response.inverter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.Inverter;
import com.samhwan.dashboard.repository.InverterYearlyRow;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterYearlyResponseDto extends ResponseDto {

  private final List<InverterYearlyRow> year;

  private GetUserInverterYearlyResponseDto(List<InverterYearlyRow> year) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.year = year;
  }

  public static ResponseEntity<GetUserInverterYearlyResponseDto> success(List<InverterYearlyRow> year) {
    return ResponseEntity.status(HttpStatus.OK).body(new GetUserInverterYearlyResponseDto(year));
  }
  @Getter
  @Builder
  public static class InverterYearlyRow{

  private final Long month;
  private final Long plantId;
  private final Long invId;
  private final Double totalValue;
  private final Long samples;
    public InverterYearlyRow( Long month,Long plantId,
      Long invId,Double totalValue, Long samples
    ) {
        this.month = month;
        this.plantId = plantId;
        this.invId = invId;
        this.totalValue = totalValue;
        this.samples = samples;
    }
  }
}
