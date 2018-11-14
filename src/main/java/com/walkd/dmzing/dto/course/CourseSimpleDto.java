package com.walkd.dmzing.dto.course;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseSimpleDto {
    private Long id;
    private String title;
    private String mainDescription;
    private Boolean isPicked;
}
