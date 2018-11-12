package com.walkd.dmzing.dto.review;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ReviewDto implements SimpleReviewDto, DetailReviewDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "dmzing 후기", position = 1)
    private String title;

    @ApiModelProperty(example = "dmzing.png", position = 2)
    private String thumbnailUrl;

    @ApiModelProperty(example = "1", position = 3)
    private Long courseId;

    @ApiModelProperty(hidden = true)
    private Long createdAt;

    @ApiModelProperty(example = "1522222", position = 4)
    private Long startAt;

    @ApiModelProperty(example = "1533333", position = 5)
    private Long endAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostDto> postDto;

    @ApiModelProperty(hidden = true)
    private Boolean like;

    @ApiModelProperty(hidden = true)
    private Long likeCount;

    public Review toEntity() {
        return Review.builder().title(title)
                .thumbnailUrl(thumbnailUrl)
                .posts(postDto.stream().map(postInnerDto -> postInnerDto.toEntity()).collect(Collectors.toList()))
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }
}
