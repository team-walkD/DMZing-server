package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import com.walkd.dmzing.exception.NotEnoughMoneyException;
import com.walkd.dmzing.exception.NotMatchedCourseException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    public static final Long DEFAULT_COURSE_ID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Type type;

    private String title;

    private String mainDescription;

    private String subDescription;

    private String imageUrl;

    private String mainImageUrl;

    private String lineImageUrl;

    private String backgroundImageUrl;

    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Place> places;

    private Level level;

    private Double estimatedTime;

    private Long price;

    private String backgroundGifUrl;

    @Builder
    public Course(Type type, String title, String mainDescription, String subDescription, String imageUrl, String mainImageUrl,
                  String lineImageUrl, String backgroundImageUrl, List<Place> places, Level level, Double estimatedTime, Long price,
                  String backgroundGifUrl) {
        this.type = type;
        this.title = title;
        this.mainDescription = mainDescription;
        this.subDescription = subDescription;
        this.imageUrl = imageUrl;
        this.mainImageUrl = mainImageUrl;
        this.lineImageUrl = lineImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.places = places;
        this.level = level;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.backgroundGifUrl = backgroundGifUrl;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<PlaceDto> makePlaceList(MissionHistory missionHistory) {
        List<Place> sortedPlaces = places.stream().sorted(Comparator.comparing(Place::getSequence)).collect(Collectors.toList());

        if (missionHistory == null)
            return new ArrayList<>(Arrays.asList(sortedPlaces.get(0).toPlaceDto().deleteInfo()));

        Place currentPlace = sortedPlaces.stream()
                .filter(place -> place.equals(missionHistory.getPlace()))
                .collect(Collectors.toList()).get(0);

        return currentPlace.toPlaceDtos(sortedPlaces.stream().map(place -> place.toPlaceDto()).collect(Collectors.toList()));
    }

    public Long isEnoughMoney(Long dp) {
        Long money = dp - price;

        if (money < 0) throw new NotEnoughMoneyException();

        return money;
    }

    public CourseMainDto toCourseMainDto(Long pickCount, Boolean isPurchased) {
        return CourseMainDto.builder()
                .id(id)
                .title(type.getTypeName())
                .imageUrl(mainImageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .price(price)
                .pickCount(pickCount)
                .isPurchased(isPurchased)
                .build();
    }

    public CourseDetailDto toCourseDetailDto(Long count,Long pickCount) {
        return CourseDetailDto.builder()
                .id(id)
                .title(type.getTypeName())
                .imageUrl(mainImageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .price(price)
                .level(level.getLevelName())
                .estimatedTime(estimatedTime)
                .backgroundImageUrl(backgroundImageUrl)
                .backgroundGifUrl(backgroundGifUrl)
                .places(places.stream().map(place -> place.toPlaceDto().deleteDetailInfo())
                        .sorted(Comparator.comparing(PlaceDto::getSequence)).collect(Collectors.toList()))
                .reviewCount(count)
                .pickCount(pickCount)
                .build();
    }

    public CourseDetailDto toCourseDetailDto(Long reviewCount, Long pickCount, MissionHistory missionHistory) {
        return CourseDetailDto.builder()
                .id(id)
                .title(type.getTypeName())
                .imageUrl(mainImageUrl)
                .lineImageUrl(lineImageUrl)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .price(price)
                .level(level.getLevelName())
                .estimatedTime(estimatedTime)
                .backgroundImageUrl(backgroundImageUrl)
                .backgroundGifUrl(backgroundGifUrl)
                .places(makePlaceList(missionHistory))
                .reviewCount(reviewCount)
                .pickCount(pickCount)
                .build();
    }

    public CourseSimpleDto toCourseSimpleDto(Boolean pick) {
        return CourseSimpleDto.builder()
                .title(type.getTypeName())
                .id(id)
                .mainDescription(mainDescription)
                .isPicked(pick)
                .build();
    }

    public Place getCheckPlace(Long pid) {
        List<Place> checkPlaces = places.stream().filter(place -> {
            return place.isEqualToId(pid);
        }).collect(Collectors.toList());
        if (checkPlaces.size() != 0) {
            return checkPlaces.get(0);
        }
        throw new NotMatchedCourseException();
    }
}
