package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Post;
import com.walkd.dmzing.domain.PostImg;
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
public class PostDto {
    @ApiModelProperty(example = "1", position = 1)
    private Long day;

    @ApiModelProperty(example = "오리고기먹자", position = 2)
    private String title;

    @ApiModelProperty(example = "맛있다.", position = 3)
    private String content;

    @ApiModelProperty(example = "[dmzing.png,dmzing2.png]", position = 2)
    private List<String> postImgUrl;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .day(day)
                .postImgs(postImgUrl.stream().map(postInnerImgUrl -> new PostImg(postInnerImgUrl)).collect(Collectors.toList()))
                .build();
    }
}
