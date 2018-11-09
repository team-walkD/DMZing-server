package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn
    private Course course;

    private Double latitude;
    private Double longitude;

    @Lob
    private String letterContent;

    private Long reward;

    @Builder
    public Place(String name, Course course, Double latitude, Double longitude, String letterContent, Long reward) {
        this.name = name;
        this.course = course;
        this.latitude = latitude;
        this.longitude = longitude;
        this.letterContent = letterContent;
        this.reward = reward;
    }
}
