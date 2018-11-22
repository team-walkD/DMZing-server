package com.walkd.dmzing.dto.user;

import com.walkd.dmzing.dto.dp.DpHistoryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserDpInfoDto {
    private Long totalDp;

    private List<DpHistoryDto> dpHistoryDtos;
}
