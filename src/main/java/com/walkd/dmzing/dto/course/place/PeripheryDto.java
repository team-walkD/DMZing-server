package com.walkd.dmzing.dto.course.place;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.walkd.dmzing.config.CommonConfig;
import com.walkd.dmzing.domain.Periphery;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class PeripheryDto {
    public static final String URI = new StringBuilder()
            .append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=%s")
            .append("&contentTypeId=%d&mapX=%s&mapY=%s&radius=10000&listYN=Y")
            .append("&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=1&pageNo=1&_type=json").toString();
    public static List<Integer> codes = new ArrayList<>(Arrays.asList(39, 32, 15));

    private String title;
    private String firstimage;
    private Long contenttypeid;

    public static PeripheryDto createDto(JsonElement jsonElement, Gson gson) {
        String jsonString = CommonConfig.getJsonString(jsonElement);
        if (jsonString == null) {
            return PeripheryDto.builder().build();
        }
        return gson.fromJson(jsonString, PeripheryDto.class);
    }

    public Periphery toEntity() {
        return Periphery.builder()
                .title(title)
                .imageUrl(firstimage)
                .contentTypeId(contenttypeid)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeripheryDto that = (PeripheryDto) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(firstimage, that.firstimage) &&
                Objects.equals(contenttypeid, that.contenttypeid);
    }
}
