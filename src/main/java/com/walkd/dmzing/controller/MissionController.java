package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.service.MissionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
}
