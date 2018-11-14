package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.course.PlaceDto;
import com.walkd.dmzing.service.CourseService;
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
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final MissionService missionService;

    @ApiOperation(value = "전체 코스 종류 및 정보 보기", notes = "모든 코스 각각에 대한 몇 가지 정보, 픽 개수, 유저의 구매여부를 확인할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<CourseMainDto>> showCourses(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(courseService.showCourses(authentication.getPrincipal().toString()));
    }

    @ApiOperation(value = "코스 상세 보기", notes = "코스 아이디를 보내면 코스의 상세 정보를 볼 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{cid}")
    public ResponseEntity<CourseDetailDto> showCourseDetail(@PathVariable Long cid, @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(courseService.showCourseDetail(cid, authentication.getPrincipal().toString()));
    }

    @ApiOperation(value = "코스 내 장소 보기", notes = "코스 아이디를 보내면 해당 코스의 장소들을 볼 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{cid}/places")
    public ResponseEntity<List<PlaceDto>> showPlacesInCourse(@PathVariable Long cid) {
        return ResponseEntity.ok().body(courseService.showPlacesInCourse(cid));
    }

    @ApiOperation(value = "코스 픽하기", notes = "코스 아이디를 보내면 해당 코스기 픽되고 코스에 대한 정보를 받을 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "픽 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/pick/{cid}")
    public ResponseEntity<CourseDetailDto> pickCourse(@PathVariable Long cid, @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(missionService.pickCourse(cid, authentication.getPrincipal().toString()));
    }

}
