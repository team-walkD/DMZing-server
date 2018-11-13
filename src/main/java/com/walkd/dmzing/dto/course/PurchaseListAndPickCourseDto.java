package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class PurchaseListAndPickCourseDto {

    private List<CourseSimpleDto> purchaseList;
    private CourseDetailDto pickCourse;

}
