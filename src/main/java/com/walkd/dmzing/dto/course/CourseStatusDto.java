package com.walkd.dmzing.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class CourseStatusDto {

    private String typeName;
    private String mainDescription;
    private Long pickCount;
    private Boolean isPurchased;

}
