package com.walkd.dmzing.dto.course.place;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.walkd.dmzing.config.CommonConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceInfoApiDto {
    public static final String URI = new StringBuilder()
            .append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=%s")
            .append("&contentTypeId=%d&contentId=%d&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json").toString();

    private String parkingculture;
    private String restdateculture;
    private String infocenterculture;

    public static PlaceInfoApiDto createDto(JsonElement jsonElement, Gson gson){
        return gson.fromJson(CommonConfig.getJsonString(jsonElement), PlaceInfoApiDto.class);
    }
}
