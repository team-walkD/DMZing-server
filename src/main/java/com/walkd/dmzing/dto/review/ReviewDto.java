package com.walkd.dmzing.dto.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.walkd.dmzing.domain.Review;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto implements DetailReviewDto {

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

    public ReviewDto(Review review,String email) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.thumbnailUrl = review.getThumbnailUrl();
        this.courseId = review.getCourse().getId();
        this.createdAt = review.getCreatedAt().getTime();
        this.startAt = review.getStartAt();
        this.endAt = review.getEndAt();
        this.postDto = review.getPosts().stream().map(post -> post.toDto()).collect(Collectors.toList());
        this.like = review.isLike(email);
        this.likeCount = review.getReviewLikes().stream().count();
    }

    public Review toEntity() {
        return Review.builder().title(title)
                .thumbnailUrl(thumbnailUrl)
                .posts(postDto.stream().map(postInnerDto -> postInnerDto.toEntity()).collect(Collectors.toList()))
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }
}
