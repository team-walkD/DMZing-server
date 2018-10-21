package com.walkd.dmzing.dto.review;

import com.walkd.dmzing.domain.Post;
import com.walkd.dmzing.domain.PostImg;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PostDto {
    private Long day;
    private String title;
    private String content;
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
