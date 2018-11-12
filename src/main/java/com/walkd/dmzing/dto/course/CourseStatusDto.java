package com.walkd.dmzing.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//? Builder와 Setter의 차이
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStatusDto {

    private String typeName;
    private String mainDescription;
    private Long pickCount;
    private Boolean isPurchased;

}
