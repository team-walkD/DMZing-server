package com.walkd.dmzing.dto.course;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceDto {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String hint;
    private String letterContent;
    private String letterTitle;
    private String letterImageUrl;
    private Long reward;
    private Long contentId;
    private Long tourTypeId;
    private Integer sequence;

}
