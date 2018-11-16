package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.exception.LetterDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double latitude;

    private Double longitude;

    private String hint;

    private String letterImageUrl;

    private Long reward;

    private Integer sequence;


    @Builder
    public Place(String title, Double latitude, Double longitude, String hint, String letterImageUrl, Long reward, Integer sequence) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hint = hint;
        this.letterImageUrl = letterImageUrl;
        this.reward = reward;
        this.sequence = sequence;
    }

    public PlaceDto toPlaceDto() {
        return PlaceDto.builder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .hint(hint)
                .letterImageUrl(letterImageUrl)
                .reward(reward)
                .sequence(sequence)
                .build();
    }

    public LetterDto toLetterDto() {
        return LetterDto.builder().letterImageUrl(letterImageUrl).build();
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

        for (int i = count; i < len; i++) {
            sorted.remove(count);
        }

        return sorted;
    }
}
