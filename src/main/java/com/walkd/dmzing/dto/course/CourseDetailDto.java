package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Level;
import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.dto.review.ReviewCountDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private Level level;
    private Double estimatedTime;
    private Long price;

    private List<PlaceDto> places;
    private ReviewCountDto reviewcount;

}
