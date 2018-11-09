package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.Type;
import lombok.Getter;

@Getter
public class CourseMainResponseDto {

    private Type type;
    private String mainDescription;
    private String subDescription;
    private String imageUrl;

    public CourseMainResponseDto(Course entity) {
        type = entity.getType();
        mainDescription = entity.getMainDescription();
        subDescription = entity.getSubDescription();
        imageUrl = entity.getImageUrl();
    }
}
