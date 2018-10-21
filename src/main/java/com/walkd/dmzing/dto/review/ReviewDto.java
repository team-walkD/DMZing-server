package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private String title;
    private String thumbnailUrl;
    private Long courseId;
    private List<PostDto> postDto;

    public Review toEntity(){
        return Review.builder().title(title)
                .thumbnailUrl(thumbnailUrl).build();
    }
}
