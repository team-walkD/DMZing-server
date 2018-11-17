package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.dto.course.place.PlaceApiDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import com.walkd.dmzing.dto.mission.MissionDto;
import com.walkd.dmzing.service.MissionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @ApiOperation(value = "코스 구매 목록 및 픽된 코스 상세 정보 보기", notes = "해당 유저가 구매한 코스 목록과 현재 픽되어있는 코스의 상세 정보를 보여줍니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<PurchaseListAndPickCourseDto> showPurchaseListAndPickedCourse(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(missionService.showPurchaseListAndPickCourse(authentication.getPrincipal().toString()));
    }

    @ApiOperation(value = "미션 시도", notes = "미션 성공시, 보상 및 코스 정보를 내려준다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<List<PlaceDto>> findLetter(@ApiIgnore Authentication authentication,@RequestBody MissionDto missionDto) {
        return ResponseEntity.ok().body(missionService.filterSuccessPlaces(authentication.getPrincipal().toString(),missionDto));
    }
}
