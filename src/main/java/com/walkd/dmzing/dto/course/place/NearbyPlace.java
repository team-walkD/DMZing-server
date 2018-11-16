package com.walkd.dmzing.dto.course.place;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.walkd.dmzing.config.CommonConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class NearbyPlace {
    public static final String URI = new StringBuilder()
            .append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=%s")
            .append("&contentTypeId=%d&mapX=%s&mapY=%s&radius=10000&listYN=Y")
            .append("&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=1&pageNo=1&_type=json").toString();
    public static List<Integer> codes = new ArrayList<>(Arrays.asList(39,32,15));

    private String title;
    private String firstimage;
    private String contenttypeid;

    public static NearbyPlace createDto(JsonElement jsonElement, Gson gson) {
        return gson.fromJson(CommonConfig.getJsonString(jsonElement), NearbyPlace.class);
    }
}
