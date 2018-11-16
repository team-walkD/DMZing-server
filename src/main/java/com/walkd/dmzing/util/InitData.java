package com.walkd.dmzing.util;

import com.walkd.dmzing.dto.course.place.PlaceSubDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class InitData {
    public static final List<PlaceSubDto> datePlaceDtos = new ArrayList<>(Arrays
            .asList(PlaceSubDto.builder().hint("힌트1").letterImageUrl("편지url1").reward(300L)
                    .sequence(1).contentId(778140).contentTypeId(14).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(400L)
                    .sequence(1).contentId(132006).contentTypeId(38).build(),
                    PlaceSubDto.builder().hint("힌트3").letterImageUrl("편지url3").reward(300L)
                    .sequence(1).contentId(264481).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트4").letterImageUrl("편지url4").reward(400L)
                    .sequence(1).contentId(125759).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트5").letterImageUrl("편지url5").reward(500L)
                    .sequence(1).contentId(2385062).contentTypeId(14).build()));
}
