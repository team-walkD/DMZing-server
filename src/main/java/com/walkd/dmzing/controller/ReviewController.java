package com.walkd.dmzing.controller;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.dto.review.*;
import com.walkd.dmzing.service.PhotoReviewService;
import com.walkd.dmzing.service.ReviewService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final PhotoReviewService photoReviewService;

    @ApiOperation(value = "리뷰작성", notes = "리뷰에 날짜별 글을 작성 후 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "작성 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
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

    @ApiOperation(value = "리뷰 이미지 등록", notes = "썸네일 이미지 및 후기 이미지를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "저장 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/images")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ImageDto(reviewService.uploadImg(multipartFile)));
    }

    @ApiOperation(value = "이미지 삭제", notes = "s3에 들어있는 이미지를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @DeleteMapping("/images")
    public ResponseEntity<Void> removeImage(@RequestParam("imageUrl") String imageUrl) {
        reviewService.removeImage(imageUrl);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "리뷰 전체보기", notes = "리뷰를 최신순으로 30개씩 보여줍니다. * 마지막 인덱스를 넣어서 보내야합니다. 없을시 0을 넣어서 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/last/{rid}/course/{type}")
    public ResponseEntity<List<SimpleReviewDto>> showReviews(@PathVariable Long rid, @PathVariable Type type, @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(reviewService.showReviews(rid, type, authentication.getPrincipal().toString()));
    }


    @ApiOperation(value = "리뷰 상세보기", notes = "보고 싶은 리뷰 id를 보내면 리뷰에 상세한 내용을 볼 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{rid}")
    public ResponseEntity<DetailReviewDto> showReview(@PathVariable Long rid, @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok().body(reviewService.showReview(rid, authentication.getPrincipal().toString()));
    }

    @ApiOperation(value = "리뷰 수 보기", notes = "코스별 리뷰수를 보여줍니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/count")
    public ResponseEntity<List<ReviewCountDto>> showReview() {
        return ResponseEntity.ok().body(reviewService.showReviewCount());
    }


    @ApiOperation(value = "사진리뷰 작성", notes = "사진 리뷰를 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "작성 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/photo")
    public ResponseEntity createPhotoReview(@RequestBody PhotoReviewDto photoReviewDto, @ApiIgnore Authentication authentication) {
        photoReviewService.createPhotoReviewDto(photoReviewDto, authentication.getPrincipal().toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(value = "사진리뷰 전체보기", notes = "사진리뷰를 최신순으로 30개씩 보여줍니다. * 마지막 인덱스를 넣어서 보내야합니다. 없을시 0을 넣어서 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "호출 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/photo/last/{pid}/course/{type}")
    public ResponseEntity<List<PhotoReviewDto>> showPhotoReviews(@PathVariable Long pid, @PathVariable Type type) {
        return ResponseEntity.ok(photoReviewService.showPhotoReviewDtos(pid, type));
    }

    @ApiOperation(value = "좋아요", notes = "좋아요가 안 눌린 상태에서 누르면 좋아요(true), 좋아요가 눌린상태에서 누르면 취소된다(false)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "좋아요 생성 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/like/{rid}")
    public ResponseEntity<ReviewLikeDto> createLikeReviews(@PathVariable Long rid, @ApiIgnore Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ReviewLikeDto(reviewService.createReviewLike(rid, authentication.getPrincipal().toString())));
    }
}
