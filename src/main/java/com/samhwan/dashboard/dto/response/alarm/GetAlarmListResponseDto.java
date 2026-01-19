package com.samhwan.dashboard.dto.response.alarm;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.Alarm;

import lombok.Getter;


@Getter
public class GetAlarmListResponseDto extends ResponseDto {

    private final List<AlarmSummary> alarms;
    private final long totalElements;
    private final int totalPages;

    
    private GetAlarmListResponseDto(List<AlarmSummary> alarms, Page<Alarm> page) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.alarms = alarms;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
    public static ResponseEntity<GetAlarmListResponseDto> success(Page<Alarm> page){
        List<AlarmSummary> list = page.getContent().stream()
                                    .map(AlarmSummary::fromEntity)
                                    .toList();
        GetAlarmListResponseDto result =  new GetAlarmListResponseDto(list, page);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Getter
    public static class AlarmSummary {
        private int id;
        private int plantId;
        private String deviceType;
        private String deviceId;
        private String occurredAt;     // 화면에 찍기 쉬운 문자열로
        private String alarmType;      // 발생/변경/해제
        private String alarmMessage;

        public static AlarmSummary fromEntity(Alarm a) {
        AlarmSummary s = new AlarmSummary();
        s.id = a.getId();
        s.plantId = a.getPlant().getPlantId();
        s.deviceType = a.getDeviceType();
        s.deviceId = a.getDeviceId();
        s.occurredAt = a.getRegdate().toString(); // 필요하면 포맷터 적용
        s.alarmType = switch (a.getAlarmFlag()) {
            case "1" -> "발생";
            case "2" -> "변경";
            case "3" -> "해제";
            default -> a.getAlarmFlag();
        };
        s.alarmMessage = a.getAlarmMessage();
        return s;
        }
    }
}
