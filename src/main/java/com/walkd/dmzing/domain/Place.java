package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.PlaceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
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

    private String letterTitle;

    private String letterImageUrl;

    private Long reward;

    private Long contentId;

    private Long tourTypeId;

    private Integer sequence;


    @Builder
    public Place(String name, Double latitude, Double longitude, String hint, String letterContent, String letterTitle, String letterImageUrl, Long reward, Long contentId, Long tourTypeId, Integer sequence) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hint = hint;
        this.letterContent = letterContent;
        this.letterTitle = letterTitle;
        this.letterImageUrl = letterImageUrl;
        this.reward = reward;
        this.contentId = contentId;
        this.tourTypeId = tourTypeId;
        this.sequence = sequence;
    }

    public PlaceDto toPlaceDto() {
        return PlaceDto.builder()
                .id(id)
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .hint(hint)
                .letterContent(letterContent)
                .letterTitle(letterTitle)
                .letterImageUrl(letterImageUrl)
                .reward(reward)
                .contentId(contentId)
                .tourTypeId(tourTypeId)
                .sequence(sequence)
                .build();
    }

    public PlaceDto toPlaceDto(Place place) {
        return PlaceDto.builder()
                .id(place.getId())
                .name(place.getName())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .hint(place.getHint())
                .letterContent(place.getLetterContent())
                .letterTitle(place.getLetterTitle())
                .letterImageUrl(place.getLetterImageUrl())
                .reward(place.getReward())
                .contentId(place.getContentId())
                .tourTypeId(place.getTourTypeId())
                .sequence(place.getSequence())
                .build();
    }

    public PlaceDto toPlaceDto(List<MissionHistory> missionHistories) {
        return missionHistories.stream()
                .filter(missionHistory -> missionHistory.getPlace().equals(this))
                .collect(Collectors.toList())
                .get(0)
                .getPlace()
                .toPlaceDto();
    }

    public List<PlaceDto> toPlaceDtos(List<PlaceDto> sorted) {
        int count = 0;
        int len = sorted.size();

        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).equals(this.toPlaceDto())) {
                count = i + 1;
                sorted.get(count).deleteInfo();
                count++;
            }
        }

        for (int i = count; i < len ; i++) {
            sorted.remove(count);
        }

        return sorted;
    }
}
