package com.walkd.dmzing.dto.review;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public interface DetailReviewDto {
    @ApiModelProperty(example = "1" , position = 1)
    Long getId();

    @ApiModelProperty(example = "dmzing 후기", position = 2)
    String getTitle();

    @ApiModelProperty(example = "dmzing.png", position = 3)
    String getThumbnailUrl();

    @ApiModelProperty(example = "15203333", position = 4)
    Long getCreatedAt();

    @ApiModelProperty(example = "1", position = 5)
    Long getCourseId();

    @ApiModelProperty(example = "1522222", position = 6)
    Long getStartAt();

    @ApiModelProperty(example = "1533333", position = 7)
    Long getEndAt();

    @ApiModelProperty(position = 8)
    List<PostDto> getPostDto();
}
