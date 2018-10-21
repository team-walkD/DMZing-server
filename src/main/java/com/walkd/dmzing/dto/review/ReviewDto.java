package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ReviewDto {
    private String title;
    private String thumbnailUrl;
    private Long courseId;
    private List<PostDto> postDto;

    public Review toEntity() {
        return Review.builder().title(title)
                .thumbnailUrl(thumbnailUrl)
                .posts(postDto.stream().map(postInnerDto -> postInnerDto.toEntity()).collect(Collectors.toList()))
                .build();
    }
}
