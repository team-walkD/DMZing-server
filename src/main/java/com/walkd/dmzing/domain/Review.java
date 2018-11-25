package com.walkd.dmzing.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTime {

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

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReviewLike> reviewLikes;

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

    public Boolean isLike(String email){
        return reviewLikes.stream().anyMatch(reviewLike -> reviewLike.isMyLike(email));
    }
}
