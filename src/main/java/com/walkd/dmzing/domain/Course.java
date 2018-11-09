package com.walkd.dmzing.domain;

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

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PurchasedCourseByUser> purchasedCoursesByUser;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Place> places;

    private Level level;

    private Integer estimatedTime;

    //todo:좋아요

    @Builder

    public Course(Type type, String mainDescription, String subDescription, String imageUrl, Level level, Integer estimatedTime) {
        this.type = type;
        this.mainDescription = mainDescription;
        this.subDescription = subDescription;
        this.imageUrl = imageUrl;
        this.level = level;
        this.estimatedTime = estimatedTime;
    }
}
