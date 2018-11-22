package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.PhotoReview;
import com.walkd.dmzing.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoReviewDto {

    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(example = "1511122", position = 2)
    private Long startAt;

    @ApiModelProperty(example = "dmzing.png", position = 7)
    private String imageUrl;

    @ApiModelProperty(example = "평화전망대", position = 4)
    private String placeName;

    @ApiModelProperty(example = "#아아아#캬캬캬", position = 5)
    private String tag;

    @ApiModelProperty(example = "1", position = 6)
    private Long courseId;

    public PhotoReview toEntity(User user, Course course) {
        return PhotoReview.builder()
                .user(user)
                .imageUrl(imageUrl)
                .startDate(startAt)
                .placeName(placeName)
                .tag(tag)
                .course(course)
                .build();
    }
}
