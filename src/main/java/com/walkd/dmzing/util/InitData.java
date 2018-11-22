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
            .asList(PlaceSubDto.builder().hint("가장 대표적인 통일 안보관광지, 임진각! 평화로운 임진각을 돌아보며, 모양새는 다소 투박하지만 깊은 의미를 가진 '이 다리' 위에서 첫 번째 편지를 찾으세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542635141870_imjingak.png").reward(70L)
                    .sequence(1).contentId(127548).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("1973년부터 여기에 있어 온 통일공원. 소중한 사람과 함께 고즈넉한 공원을 누비며 여러개의 돌들이 단상을 감싸며 서 있고, 그 앞에 항아리가 있는 '望拜壇' 앞에서 두 번째 편지를 찾으세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637319216_tongil_park.png").reward(80L)
                    .sequence(2).contentId(127001).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("아름다운 임진각, 이야기가 있는 통일공원, 다음은 신나게 즐길 차례! '통일동산 카트랜드'라고 적힌 카트랜드의 매표소 앞에서 세 번째 편지를 찾으세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637556154_cart_land.png").reward(80L)
                    .sequence(3).contentId(131919).contentTypeId(28).build(),
                    PlaceSubDto.builder().hint("패션 뿐만 아니라 키덜트 취향을 저격하는 다양한 캐릭터샵에 맛집까지! 이 곳은 천국인가요? 파주 프리미엄 아울렛을 마음껏 즐긴 뒤, 해가 지면 분수대가 있는 광장으로 나와 마지막 편지를 찾으세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637580237_paju_outlet.png").reward(100L)
                    .sequence(100).contentId(1238580).contentTypeId(38).build()));

    public static final List<PlaceSubDto> historyPlaceDtos = new ArrayList<>(Arrays
            .asList(PlaceSubDto.builder().hint("힌트1").letterImageUrl("편지url1").reward(70L)
                            .sequence(1).contentId(778140).contentTypeId(14).build(),
                    PlaceSubDto.builder().hint("힌트2").letterImageUrl("편지url2").reward(90L)
                            .sequence(2).contentId(125752).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트3").letterImageUrl("편지url3").reward(950L)
                            .sequence(3).contentId(129591).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("힌트4").letterImageUrl("편지url4").reward(110L)
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
