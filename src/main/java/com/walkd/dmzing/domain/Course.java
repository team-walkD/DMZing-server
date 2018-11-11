package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.CourseMainDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Type type;

    private String mainDescription;

    private String subDescription;

    private String imageUrl;

    private String lineImageUrl;

    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Place> places;

    private Level level;

    private Double estimatedTime;

    @Builder
    public Course(Type type, String mainDescription, String subDescription, String imageUrl, String lineImageUrl, Level level, Double estimatedTime) {
        this.type = type;
        this.mainDescription = mainDescription;
        this.subDescription = subDescription;
        this.imageUrl = imageUrl;
        this.lineImageUrl = lineImageUrl;
        this.level = level;
        this.estimatedTime = estimatedTime;
    }

    public CourseMainDto toCourseMainDto(User user) {
        return CourseMainDto.builder()
                .id(id)
                .type(type)
                .imageUrl(imageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .pickCount()
                .isPurchased()
                .build();
    }
}
