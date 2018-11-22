package com.walkd.dmzing.dto.course;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseMainDto {

    private Long id;
    private String title;
    private String mainDescription;
    private String subDescription;
    private String imageUrl;
    private String lineImageUrl;
    private Long price;

    private Long pickCount;
    private Boolean isPurchased;

}
