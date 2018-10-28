package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Review;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto implements SimpleReviewDto {
    //todo id는 삽입할때 빼도록 적용
    private Long id;

    @ApiModelProperty(example = "dmzing 후기", position = 1)
    private String title;

    @ApiModelProperty(example = "dmzing.png", position = 2)
    private String thumbnailUrl;

    @ApiModelProperty(example = "1", position = 3)
    private Long courseId;

    private Long createdAt;

    private List<PostDto> postDto;

    public Review toEntity() {
        return Review.builder().title(title)
                .thumbnailUrl(thumbnailUrl)
                .posts(postDto.stream().map(postInnerDto -> postInnerDto.toEntity()).collect(Collectors.toList()))
                .build();
    }
}
