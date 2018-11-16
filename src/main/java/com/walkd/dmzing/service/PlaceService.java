package com.walkd.dmzing.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.walkd.dmzing.dto.course.place.NearbyPlace;
import com.walkd.dmzing.dto.course.place.PlaceApiDto;
import com.walkd.dmzing.dto.course.place.PlaceInfoApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {
    @Value("${spring.config.openapi.key}")
    public String apiKey;

    private final JsonParser jsonParser;

    private final RestTemplate restTemplate;

    private final Gson gson;

    public String createPlace() {
        PlaceApiDto placeApiDto = PlaceApiDto.createDto(getJsonElement(PlaceApiDto.URI, 14, 778140), gson);
        PlaceInfoApiDto placeInfoApiDto = PlaceInfoApiDto.createDto(getJsonElement(PlaceInfoApiDto.URI, 14, 778140), gson);

        placeApiDto.setPlaceInfoApiDto(placeInfoApiDto);

        List<NearbyPlace> nearbyPlaces = NearbyPlace.codes.stream()
                .map(code -> NearbyPlace.createDto(getJsonElement(placeApiDto.getNearByUri(code, apiKey)), gson))
                .collect(Collectors.toList());

        return placeApiDto.getAddr1();
    }

    private JsonElement getJsonElement(String uri, Integer contentTypeId, Integer contentId) {
        String json = restTemplate.getForObject(URI.create(String.format(uri, apiKey, contentTypeId, contentId)), String.class);
        return jsonParser.parse(json);
    }

    private JsonElement getJsonElement(URI uri) {
        String json = restTemplate.getForObject(uri, String.class);
        return jsonParser.parse(json);
    }
}
