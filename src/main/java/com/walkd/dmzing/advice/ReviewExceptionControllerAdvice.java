package com.walkd.dmzing.advice;

import com.walkd.dmzing.exception.NotFoundReviewException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ReviewExceptionControllerAdvice {

    @ExceptionHandler(NotFoundReviewException.class)
    public ResponseEntity notFoundReviewException(NotFoundReviewException exception) {
        log.debug("[NotFoundReviewException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
