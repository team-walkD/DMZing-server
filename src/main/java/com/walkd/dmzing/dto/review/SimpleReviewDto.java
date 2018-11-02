package com.walkd.dmzing.dto.review;

import io.swagger.annotations.ApiModelProperty;

public interface SimpleReviewDto {
    //todo 직렬화시 여기 getter만 나가도록 적용
    @ApiModelProperty(example = "1", position = 1)
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


    @ApiModelProperty(example = "true", position = 8)
    Boolean getLike();

    @ApiModelProperty(example = "10", position = 9)
    Long getLikeCount();
}
