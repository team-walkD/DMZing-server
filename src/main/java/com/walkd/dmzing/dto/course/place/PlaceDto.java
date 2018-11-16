package com.walkd.dmzing.dto.course.place;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class PlaceDto {
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
    private String description;

    private String parking;
    private String restDate;
    private String infoCenter;

    private List<PeripheryDto> peripheries;


    public PlaceDto deleteDetailInfo() {
        hint = null;
        letterImageUrl = null;
        return this;
    }

    public PlaceDto deleteInfo() {
        latitude = null;
        longitude = null;
        letterImageUrl = null;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceDto placeDto = (PlaceDto) o;
        return Objects.equals(id, placeDto.id) &&
                Objects.equals(hint, placeDto.hint) &&
                Objects.equals(letterImageUrl, placeDto.letterImageUrl) &&
                Objects.equals(reward, placeDto.reward) &&
                Objects.equals(sequence, placeDto.sequence) &&
                Objects.equals(mainImageUrl, placeDto.mainImageUrl) &&
                Objects.equals(subImageUrl, placeDto.subImageUrl) &&
                Objects.equals(title, placeDto.title) &&
                Objects.equals(latitude, placeDto.latitude) &&
                Objects.equals(longitude, placeDto.longitude) &&
                Objects.equals(address, placeDto.address) &&
                Objects.equals(description, placeDto.description) &&
                Objects.equals(parking, placeDto.parking) &&
                Objects.equals(restDate, placeDto.restDate) &&
                Objects.equals(infoCenter, placeDto.infoCenter) &&
                Objects.equals(peripheries, placeDto.peripheries);
    }
}
