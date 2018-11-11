package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "purchased_course_by_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "course_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasedCourseByUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Boolean isPicked;

    @Builder
    public PurchasedCourseByUser(User user, Course course, Boolean isPicked) {
        this.user = user;
        this.course = course;
        this.isPicked = isPicked;
    }
}
