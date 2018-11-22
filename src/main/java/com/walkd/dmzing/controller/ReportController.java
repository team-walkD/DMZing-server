package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.report.ReportDto;
import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.service.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @ApiOperation(value = "리뷰 신고", notes = "포토리뷰, 상세리뷰 타입을 정해서 신고합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "신고 성공"),
            @ApiResponse(code = 401, message = "권한 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReportDto reportDto, @ApiIgnore Authentication authentication) {
        reportService.createReport(reportDto, authentication.getPrincipal().toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
