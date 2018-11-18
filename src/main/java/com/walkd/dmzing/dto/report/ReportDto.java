package com.walkd.dmzing.dto.report;

import com.walkd.dmzing.domain.Report;
import com.walkd.dmzing.domain.ReportType;
import com.walkd.dmzing.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReportDto {
    private Long reviewId;
    private ReportType reportType;
    private String content;

    public Report toEntity(User user) {
        return Report.builder()
                .content(content)
                .user(user)
                .reportType(reportType)
                .reviewId(reviewId)
                .build();
    }
}
