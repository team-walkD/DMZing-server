package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Review;
import io.swagger.annotations.ApiModelProperty;

public class SimpleReviewDto {
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

    @ApiModelProperty(hidden = true)
    private Boolean like;

    @ApiModelProperty(hidden = true)
    private Long likeCount;

    public SimpleReviewDto(Review review,String email) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.thumbnailUrl = review.getThumbnailUrl();
        this.courseId = review.getCourse().getId();
        this.createdAt = review.getCreatedAt().getTime();
        this.startAt = review.getStartAt();
        this.endAt = review.getEndAt();
        this.like = review.isLike(email);
        this.likeCount = review.getReviewLikes().stream().count();
    }
}
