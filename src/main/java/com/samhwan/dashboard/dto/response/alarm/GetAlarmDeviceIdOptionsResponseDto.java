package com.samhwan.dashboard.dto.response.alarm;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetAlarmDeviceIdOptionsResponseDto extends ResponseDto{

    private final List<String> deviceIds;

    private GetAlarmDeviceIdOptionsResponseDto(List<String> deviceIds) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.deviceIds = deviceIds;
    }

    public static ResponseEntity<GetAlarmDeviceIdOptionsResponseDto> success(List<String> deviceIds) {
    GetAlarmDeviceIdOptionsResponseDto result = new GetAlarmDeviceIdOptionsResponseDto(deviceIds);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
