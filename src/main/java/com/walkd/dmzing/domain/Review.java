package com.walkd.dmzing.domain;


import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
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

    private String thumbnailUrl;

    @Builder
    public Review(String title, List<Post> posts, String thumbnailUrl) {
        this.title = title;
        this.posts = posts;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Review setUser(User user){
        this.user = user;
        return this;
    }

    public Review setCourse(Course course){
        this.course = course;
        return this;
    }

}
