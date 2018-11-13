package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.review.PhotoReviewDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoReview extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeName;

    private Long startAt;

    private String imageUrl;

    @Lob
    private String tag;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Course course;

    @Builder
    public PhotoReview(String placeName, Long startDate, String imageUrl, String tag, User user, Course course) {
        this.placeName = placeName;
        this.startAt = startDate;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.user = user;
        this.course = course;
    }

    public PhotoReviewDto toDto() {
        return PhotoReviewDto.builder()
                .imageUrl(imageUrl)
                .courseId(course.getId())
                .placeName(placeName)
                .startAt(startAt)
                .tag(tag)
                .build();
    }
}
