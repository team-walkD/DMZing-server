package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Place;
import com.walkd.dmzing.domain.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CourseDetailDto {

    private Long id;
    private Type type;
    private String title;
    private String mainDescription;
    private String subDescription;
    private String imageUrl;
    private String lineImageUrl;
    private Long price;

    private List<PlaceDto> places;

}
