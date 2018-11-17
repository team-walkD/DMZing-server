package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.place.PeripheryDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import com.walkd.dmzing.dto.exception.LetterDto;
import com.walkd.dmzing.exception.NotMatchedCourseException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.net.URI;
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
    private String hint;
    private String letterImageUrl;
    private Long reward;
    private Integer sequence;

    private String mainImageUrl;
    private String subImageUrl;
    private String title;
    private Double latitude;
    private Double longitude;
    private String address;

    @Lob
    private String description;

    private String parking;
    private String restDate;
    private String infoCenter;

    @JoinColumn(name = "place_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Periphery> peripheries;

    @Builder
    public Place(String hint, String letterImageUrl, Long reward, Integer sequence,
                 String mainImageUrl, String subImageUrl, String title, Double latitude,
                 Double longitude, String address, String description, String parking,
                 String restDate, String infoCenter) {
        this.hint = hint;
        this.letterImageUrl = letterImageUrl;
        this.reward = reward;
        this.sequence = sequence;
        this.mainImageUrl = mainImageUrl;
        this.subImageUrl = subImageUrl;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.description = description;
        this.parking = parking;
        this.restDate = restDate;
        this.infoCenter = infoCenter;
    }

    public Place setPeripheries(List<Periphery> peripheries) {
        this.peripheries = peripheries;
        return this;
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
                .mainImageUrl(mainImageUrl)
                .subImageUrl(subImageUrl)
                .title(title)
                .address(address)
                .description(description)
                .parking(parking)
                .restDate(restDate)
                .infoCenter(infoCenter)
                .peripheries(peripheries.stream().map(periphery -> periphery.toPeripheryDto()).collect(Collectors.toList()))
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

    public List<PlaceDto> getRemovedPlaceDtos(List<PlaceDto> placeDtos) {
        return placeDtos.stream().filter(placeDto -> placeDto.isLatePlace(sequence)).collect(Collectors.toList());
    }

    public URI getNearByUri(Integer contentTypeId, String apiKey) {
        return java.net.URI
                .create(String.format(PeripheryDto.URI, apiKey, contentTypeId, this.longitude.toString(), this.latitude.toString()));
    }

    public boolean isEqualToId(Long pid) {
        return pid == this.id;
    }

    public Long checkSuccessed(Double latitude, Double longitude) {
        if(500 > distance(latitude,longitude)) return reward;
        throw new NotMatchedCourseException();
    }

    private  double distance(double latitude, double longitude) {
        double theta = this.longitude - longitude;

        double dist = Math.sin(deg2rad(this.latitude)) * Math.sin(deg2rad(latitude))
                + Math.cos(deg2rad(this.latitude)) * Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(theta));

        dist = rad2deg(Math.acos(dist));
        dist = dist * 60 * 1.1515;

        return (dist * 1609.344);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
