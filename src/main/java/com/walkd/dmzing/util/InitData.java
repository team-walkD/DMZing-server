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
            .asList(PlaceSubDto.builder().hint("힌트1").letterImageUrl("편지url1").reward(70L)
                    .sequence(1).contentId(127548).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(80L)
                    .sequence(2).contentId(1921229).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트3").letterImageUrl("편지url3").reward(80L)
                    .sequence(3).contentId(127001).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트4").letterImageUrl("편지url4").reward(100L)
                    .sequence(4).contentId(131919).contentTypeId(28).build(),
                    PlaceSubDto.builder().hint("힌트5").letterImageUrl("편지url5").reward(100L)
                    .sequence(100).contentId(752431).contentTypeId(12).build()));

    public static final List<PlaceSubDto> historyPlaceDtos = new ArrayList<>(Arrays
            .asList(PlaceSubDto.builder().hint("힌트1").letterImageUrl("편지url1").reward(100L)
                            .sequence(1).contentId(778140).contentTypeId(14).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(110L)
                            .sequence(100).contentId(128171).contentTypeId(12).build()));

    public static final List<PlaceSubDto> naturePlaceDtos = new ArrayList<>(Arrays
            .asList(PlaceSubDto.builder().hint("힌트1").letterImageUrl("편지url1").reward(70L)
                            .sequence(1).contentId(129304).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(80L)
                            .sequence(2).contentId(2393543).contentTypeId(28).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(90L)
                            .sequence(3).contentId(125801).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(120L)
                            .sequence(100).contentId(1626292).contentTypeId(12).build()));

}
