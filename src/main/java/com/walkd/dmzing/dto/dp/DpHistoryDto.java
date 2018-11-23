package com.walkd.dmzing.dto.dp;

import com.walkd.dmzing.domain.DpHistory;
import lombok.Getter;

@Getter
public class DpHistoryDto {
    private Long id;
    private Long createdAt;
    private String dpType;
    private Long dp;

    public DpHistoryDto(DpHistory dpHistory){
        this.id = dpHistory.getId();
        this.createdAt = dpHistory.getCreatedAt().getTime();
        this.dpType = dpHistory.getDpType();
        this.dp = getDp();
    }
}
