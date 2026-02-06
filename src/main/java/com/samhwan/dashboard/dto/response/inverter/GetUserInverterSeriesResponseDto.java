package com.samhwan.dashboard.dto.response.inverter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.Inverter;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInverterSeriesResponseDto extends ResponseDto {

  private final List<InverterView> series;

  private GetUserInverterSeriesResponseDto(List<InverterView> series) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.series = series;
  }

  public static ResponseEntity<GetUserInverterSeriesResponseDto> success(List<InverterView> series) {
    return ResponseEntity.status(HttpStatus.OK).body(new GetUserInverterSeriesResponseDto(series));
  }

    @Getter
    @Builder
    public static class InverterView{

    private final String bucketHour;
    private final Long plantId;
    private final Long invId;
    private final Double hourGenKwh;
    private final Long samples;
        public InverterView( String bucketHour,Long plantId,
          Long invId,Double hourGenKwh, Long samples
        ) {
            this.bucketHour = bucketHour;
            this.plantId = plantId;
            this.invId = invId;
            this.hourGenKwh = hourGenKwh;
            this.samples = samples;
        }
    }
}
