package com.walkd.dmzing.controller;

import com.walkd.dmzing.auth.jwt.JwtInfo;
import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import com.walkd.dmzing.dto.user.JoinUser;
import com.walkd.dmzing.dto.user.LoginUser;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.info.UserCourseInfoDto;
import com.walkd.dmzing.dto.user.info.UserDpInfoDto;
import com.walkd.dmzing.dto.user.info.UserInfoDto;

import com.walkd.dmzing.service.UserService;
import com.walkd.dmzing.util.JwtUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation(value = "일반 유저 생성", notes = "일반 유저를 생성합니다. 성공시 jwt 토큰을 헤더에 넣어서 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "유저 생성 성공", response = void.class),
            @ApiResponse(code = 400, message = "유효성 체크 에러 or 이미 가입된 이메일(String 메세지만 출력됩니다.)", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("")
    public ResponseEntity<Void> create(@Validated(JoinUser.class) @RequestBody UserDto userDto) {
        String token = JwtUtil.createToken(userService.create(userDto));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtInfo.HEADER_NAME, token);

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }


    @ApiOperation(value = "로그인", notes = "로그인합니다. 성공시 jwt 토큰을 헤더에 넣어서 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 403, message = "로그인 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/login")
    public void login(@Validated(LoginUser.class) @RequestBody LoginUser loginUser) {
    }


    @ApiOperation(value = "마이페이지 조회", notes = "마이페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> showUserInfo(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(userService.showUserInfo(authentication.getPrincipal().toString()));
    }


    @ApiOperation(value = "마이페이지 리뷰 조회", notes = "좋아요나 본인이 쓴 리뷰 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/reviews")
    public ResponseEntity<List<SimpleReviewDto>> showUserReview(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(userService.showUserReview(authentication.getPrincipal().toString()));
    }




    @ApiOperation(value = "마이페이지 코스 조회", notes = "구매한 코스 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/course")
    public ResponseEntity<List<UserCourseInfoDto>> showUserCourse(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(userService.showUserCourse(authentication.getPrincipal().toString()));
    }


    @ApiOperation(value = "마이페이지 DP 조회", notes = "DP 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/dp")

    public ResponseEntity<UserDpInfoDto> showUserDmzPoint(@ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(userService.showUserDmzPoint(authentication.getPrincipal().toString()));
    }

}
