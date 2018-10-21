package com.walkd.dmzing.domain;

import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    private String title;
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
}
