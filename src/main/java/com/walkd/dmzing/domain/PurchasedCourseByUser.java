package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.user.info.UserCourseInfoDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "purchased_course_by_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "course_id"}
                )
        }
)
@Getter
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

    public CourseMainDto toUserCourseInfoDto(Course course) {
        return CourseMainDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .type(course.getType())
                .mainDescription(course.getMainDescription())
                .subDescription(course.getSubDescription())
                .imageUrl(course.getImageUrl())
                .price(course.getPrice())
                .build();

    }
}
