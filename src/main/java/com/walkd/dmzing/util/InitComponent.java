package com.walkd.dmzing.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.course.place.PeripheryDto;
import com.walkd.dmzing.dto.course.place.PlaceApiDto;
import com.walkd.dmzing.dto.course.place.PlaceInfoApiDto;
import com.walkd.dmzing.dto.course.place.PlaceSubDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitComponent implements ApplicationRunner {

    private final CourseRepository courseRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    private final PlaceRepository placeRepository;

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final JsonParser jsonParser;

    private final RestTemplate restTemplate;

    private final Gson gson;

    @Value("${spring.config.openapi.key}")
    public String apiKey;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
//          if (!apiKey.equals("test")) {
//            courseRepository.findByType(Type.DATE).orElseThrow(NotFoundCourseException::new)
//                    .setPlaces(updatePeripheryDto(createPlaces(InitData.datePlaceDtos)));
//
//            courseRepository.findByType(Type.ADVENTURE).orElseThrow(NotFoundCourseException::new)
//                    .setPlaces(updatePeripheryDto(createPlaces(InitData.naturePlaceDtos)));
//
//            courseRepository.findByType(Type.HISTORY).orElseThrow(NotFoundCourseException::new)
//                    .setPlaces(updatePeripheryDto(createPlaces(InitData.historyPlaceDtos)));
//
//            Place place1 = placeRepository.findById(1L).get();
//            Place place2 = placeRepository.findById(2L).get();
//            PurchasedCourseByUser purchasedCourseByUser = purchasedCourseByUserRepository.findById(1L).get();
//
//            missionHistoryRepository.save(MissionHistory.builder().place(place1).purchasedCourseByUser(purchasedCourseByUser).build());
//            missionHistoryRepository.save(MissionHistory.builder().place(place2).purchasedCourseByUser(purchasedCourseByUser).build());
//        }
    }

    public List<Place> createPlaces(List<PlaceSubDto> placeDtos) {
        return placeDtos.stream().map(placeSubDto -> {
            PlaceApiDto placeApiDto = PlaceApiDto.createDto(getJsonElement(PlaceApiDto.URI, placeSubDto.getContentTypeId(), placeSubDto.getContentId()), gson);
            PlaceInfoApiDto placeInfoApiDto = PlaceInfoApiDto.createDto(getJsonElement(PlaceInfoApiDto.URI, placeSubDto.getContentTypeId(), placeSubDto.getContentId()), gson);
            return placeApiDto.toEntity(placeInfoApiDto, placeSubDto);
        }).collect(Collectors.toList());
    }

    private List<Place> updatePeripheryDto(List<Place> places) {
        return places.stream().map(place ->
                place.setPeripheries(callPeripheriesAPI(place))
        ).collect(Collectors.toList());
    }

    private JsonElement getJsonElement(String uri, Integer contentTypeId, Integer contentId) {
        URI courseUri = URI.create(String.format(uri, apiKey, contentTypeId, contentId));
        log.info(courseUri.toString());
        String json = restTemplate.getForObject(courseUri, String.class);
        return jsonParser.parse(json);
    }

    private JsonElement getJsonElement(URI uri) {
        String json = restTemplate.getForObject(uri, String.class);
        return jsonParser.parse(json);
    }

    private List<Periphery> callPeripheriesAPI(Place place) {
        return PeripheryDto.codes.stream()
                .map(code ->
                        PeripheryDto.createDto(getJsonElement(place.getNearByUri(code, apiKey)), gson).toEntity()
                ).collect(Collectors.toList());
    }
}
