package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.exception.NotFoundReviewException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ReviewExceptionControllerAdvice {

    private static final String FIELD = "REVIEW";

    @ExceptionHandler(NotFoundReviewException.class)
    public ResponseEntity<ExceptionDto> notFoundReviewException(NotFoundReviewException exception) {
        log.debug("[NotFoundReviewException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage())
                        .build());
    }
}
