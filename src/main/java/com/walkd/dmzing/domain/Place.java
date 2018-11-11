package com.walkd.dmzing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn
//    private Course course;

    private Double latitude;
    private Double longitude;

    private String hint;

    @Lob
    private String letterContent;

    private String letterImageUrl;

    private Long reward;

    private Long contentId;
    private Long tourTypeId;

    @Builder

    public Place(String name, Double latitude, Double longitude, String hint, String letterContent, String letterImageUrl, Long reward, Long contentId, Long tourTypeId) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hint = hint;
        this.letterContent = letterContent;
        this.letterImageUrl = letterImageUrl;
        this.reward = reward;
        this.contentId = contentId;
        this.tourTypeId = tourTypeId;
    }
}
