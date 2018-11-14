package com.walkd.dmzing.controller;


import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.service.CourseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CourseService courseService;

    @ApiOperation(value = "코스 주문하기", notes = "유저 돈을 확인하고 해당 코스를 살 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "주문 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/course/{cid}")
    public ResponseEntity<CourseDetailDto> showCourses(@PathVariable Long cid, @ApiIgnore Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.buyCourse(cid, authentication.getPrincipal().toString()));
    }
}
