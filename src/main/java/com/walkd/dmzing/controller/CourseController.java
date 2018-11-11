package com.walkd.dmzing.controller;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.course.CourseStatusDto;
import com.walkd.dmzing.service.CourseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @ApiOperation(value = "전체 코스 종류 및 정보 보기", notes = "모든 코스와 각각에 대한 상세 정보, 픽 개수, 유저의 구매여부를 확인할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<CourseMainDto>> showCourses(){

        return ResponseEntity.ok().body(courseService.showCourses());
    }

    @ApiOperation(value = "코스 상태 보기", notes = "코스 타입을 보내면 해당 코스에 대한 현재 픽 개수와 유저의 구매여부를 확인할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{type}")
    public ResponseEntity<CourseStatusDto> showCourseStatus(@PathVariable Type type, @ApiIgnore Authentication authentication){
        return ResponseEntity.ok().body(courseService.showCourseStatus(type, authentication.getPrincipal().toString()));
    }

}
