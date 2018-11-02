package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.review.PostDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    private String title;

    @Lob
    private String content;

    @JoinColumn(name = "post_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PostImg> postImgs;

    @Builder
    public Post(Long day, String title, String content, List<PostImg> postImgs) {
        this.day = day;
        this.title = title;
        this.content = content;
        this.postImgs = postImgs;
    }

    public PostDto toDto() {
        return PostDto.builder()
                .day(day)
                .content(content)
                .title(title)
                .postImgUrl(postImgs.stream().map(postImg -> postImg.getImgUrl()).collect(Collectors.toList()))
                .build();
    }
}
