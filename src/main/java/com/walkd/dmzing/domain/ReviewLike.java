package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Review review;

    public ReviewLike(User user, Review review) {
        this.user = user;
        this.review = review;
    }

    public boolean isMyLike(String email) {
        return this.user.getEmail().equals(email);
    }
}
