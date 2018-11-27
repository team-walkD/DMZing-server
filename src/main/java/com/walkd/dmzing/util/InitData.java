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

    public static final List<PlaceSubDto> dmzPlaceDtos = new ArrayList<>(Arrays
            .asList(PlaceSubDto.builder().hint("영화 ‘고지전’의 배경이 된 유명한 전투를 기억하시나요? 한국전쟁 당시 국군은 중공군과 철원평야 요충지인 이곳을 두고 공방전을 벌였는데요, 이곳을 기리기 위해 세워진 기념관에서 첫번째 편지를 찾아보세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542635141870_imjingak.png").reward(70L)
                            .sequence(1).contentId(731587).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("해방이후 북한정권이 철원에 세운 것으로 의미가 있는 건물을 찾아보세요~ 현재는 건물의 외벽만 남아있으니 찾기 어렵지 않을거예요!")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637319216_tongil_park.png").reward(80L)
                            .sequence(2).contentId(264485).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("휴전선 비무장지대를 비롯하여 평강고원과 북한 선전마을을 전망할 수 있는 이 전망대를 찾아가세요! 모노레일을 타고 올라가면 좀 더 편안하게 갈 수 있답니다~")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637556154_cart_land.png").reward(80L)
                            .sequence(3).contentId(561034).contentTypeId(12).build(),
                    PlaceSubDto.builder().hint("우리나라에서 두 번째로 발견된 남침용 땅굴을 찾으세요.")
                            .letterImageUrl("https://dmzing-s3.s3.ap-northeast-2.amazonaws.com/review/1542637580237_paju_outlet.png").reward(100L)
                            .sequence(100).contentId(264481).contentTypeId(12).build()));

}
