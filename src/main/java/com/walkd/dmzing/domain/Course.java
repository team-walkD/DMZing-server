package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Type type;
    private String title;

    private String mainDescription;

    private String subDescription;

    private String imageUrl;

    private String lineImageUrl;

    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Place> places;

    private Level level;

    private Double estimatedTime;

    private Long price;

    @Builder
    public Course(Type type, String title, String mainDescription, String subDescription, String imageUrl, String lineImageUrl, Level level, Double estimatedTime, Long price) {
        this.type = type;
        this.title = title;
        this.mainDescription = mainDescription;
        this.subDescription = subDescription;
        this.imageUrl = imageUrl;
        this.lineImageUrl = lineImageUrl;
        this.level = level;
        this.estimatedTime = estimatedTime;
        this.price = price;
    }

    public CourseMainDto toCourseMainDto(Long pickCount, Boolean isPurchased) {
        return CourseMainDto.builder()
                .id(id)
                .type(type)
                .title(title)
                .imageUrl(imageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .price(price)
                .pickCount(pickCount)
                .isPurchased(isPurchased)
                .build();
    }

    public CourseDetailDto toCourseDetailDto() {
        return CourseDetailDto.builder()
                .id(id)
                .type(type)
                .title(title)
                .imageUrl(imageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .price(price)
                .places(places.stream().map(place -> place.toPlaceDto()).collect(Collectors.toList()))
                .build();
    }

    public Long isEnoughMoney(Long dp) {
       Long money = dp - price;

       //todo 커스텀 익셉션.
       if(money < 0) throw new RuntimeException();

       return money;
    }
}
