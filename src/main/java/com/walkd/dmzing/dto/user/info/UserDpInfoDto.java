package com.walkd.dmzing.dto.user.info;

import com.walkd.dmzing.domain.DpHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserDpInfoDto {
    private Long totalDp;

    // TODO domain -> dto 로 수정
    private List<DpHistory> dpHistories;

    public UserDpInfoDto(Long totalDp, List<DpHistory> dpHistories) {
        this.totalDp = totalDp;
        this.dpHistories = dpHistories;
    }
}
