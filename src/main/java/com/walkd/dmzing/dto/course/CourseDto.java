package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Type type;

    private String mainDescription;

    private String subDescription;

    private String imageUrl;

    public Course toEntity() {
        return Course.builder()
                .type(type)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .imageUrl(imageUrl)
                .build();
    }
}
