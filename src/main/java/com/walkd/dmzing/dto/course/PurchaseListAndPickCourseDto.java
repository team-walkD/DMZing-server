package com.walkd.dmzing.dto.course;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PurchaseListAndPickCourseDto {

    private List<CourseSimpleDto> purchaseList;
    private CourseDetailDto pickCourse;

}
