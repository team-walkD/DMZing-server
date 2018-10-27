package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.service.ReviewService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @ApiOperation(value = "리뷰작성", notes = "리뷰에 날짜별 글을 작성 후 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "작성 성공"),
            @ApiResponse(code = 401, message = "권한 없읍"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReviewDto reviewDto, @ApiIgnore Authentication authentication) {
        reviewService.createReview(reviewDto, authentication.getPrincipal().toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
