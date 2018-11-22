package com.walkd.dmzing.dto.dp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DpHistoryDto {
    private Long id;
    private Long createdAt;
    private String dpType;
    private Long dp;
}
