package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CourseSimpleDto {
    private Long id;
    private Type type;
    private Boolean isPicked;
}
