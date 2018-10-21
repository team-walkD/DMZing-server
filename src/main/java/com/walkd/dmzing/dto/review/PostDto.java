package com.walkd.dmzing.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostDto {
    private Long day;
    private String title;
    private String content;
    private List<String> postImgUrl;

}
