package com.walkd.dmzing.domain;

import javax.persistence.*;

@Entity
@Table(
        name = "mapper",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "course_id"}
                )
        }
)
public class UserPickedMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private boolean successed;
}
