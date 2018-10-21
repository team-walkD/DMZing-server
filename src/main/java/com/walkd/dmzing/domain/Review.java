package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.util.DateUtil;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
public class Review extends DateUtil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JoinColumn(name = "review_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> posts;

    @ManyToOne
    @JoinColumn
    private Course course;

    private String thumbnailUrl;

    @Builder
    public Review(String title, List<Post> posts, Course course, String thumbnailUrl) {
        this.title = title;
        this.posts = posts;
        this.course = course;
        this.thumbnailUrl = thumbnailUrl;
    }

}
