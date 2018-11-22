package com.walkd.dmzing.dto.course.place;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceSubDto {
    private String hint;
    private String letterImageUrl;
    private Long reward;
    private Integer sequence;
    private Integer contentTypeId;
    private Integer contentId;
}
