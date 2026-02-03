// src/main/java/com/samhwan/dashboard/dto/response/report/GetReportResponseDto.java
package com.samhwan.dashboard.dto.response.inverter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetReportResponseDto extends ResponseDto {

    private final String mode;        // DAILY | MONTHLY | YEARLY
    private final String reportDate;  // 예: 2021-10-20 / 2021-10 / 2021
    private final List<ReportRow> rows;
    private final Double totalGen;
    private final Double totalHours;  // 일간일 때만 쓰고, 아니면 null

    private GetReportResponseDto(String mode, String reportDate, List<ReportRow> rows, Double totalGen, Double totalHours) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mode = mode;
        this.reportDate = reportDate;
        this.rows = rows;
        this.totalGen = totalGen;
        this.totalHours = totalHours;
    }

    public static ResponseEntity<GetReportResponseDto> success(
        String mode,
        String reportDate,
        List<ReportRow> rows,
        Double totalGen,
        Double totalHours
    ) {
        GetReportResponseDto result = new GetReportResponseDto(mode, reportDate, rows, totalGen, totalHours);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Getter
    @Builder
    public static class ReportRow {
        private String label;  // 일간: "10:00", 월간: "15", 연간: "8"
        private Double inv01;  // 인버터별 값(현재는 INV01 기준)
        private Double total;  // 전체 합계
    }
}
