package com.walkd.dmzing.domain;


import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

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

    @ManyToOne
    @JoinColumn
    private User user;

    private Long startAt;

    private Long endAt;

    private String thumbnailUrl;

    @Builder
    public Review(String title, List<Post> posts, String thumbnailUrl, Long startAt, Long endAt) {
        this.title = title;
        this.posts = posts;
        this.thumbnailUrl = thumbnailUrl;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Review setUser(User user) {
        this.user = user;
        return this;
    }

    public Review setCourse(Course course) {
        this.course = course;
        return this;
    }

    public SimpleReviewDto toSimpleDto() {
        return ReviewDto.builder()
                .id(id)
                .title(title)
                .createdAt(getCreatedAt().getTime())
                .thumbnailUrl(thumbnailUrl)
                .courseId(course.getId())
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }

    public ReviewDto toDto() {
        return ReviewDto.builder()
                .id(id)
                .title(title)
                .createdAt(getCreatedAt().getTime())
                .thumbnailUrl(thumbnailUrl)
                .startAt(startAt)
                .endAt(endAt)
                .courseId(course.getId())
                .postDto(posts.stream().map(post -> post.toDto()).collect(Collectors.toList()))
                .build();
    }
}
