package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.exception.AlreadyBuyCourseException;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundPurchaseHistoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class CourseExceptionControllerAdvice {
    private static final String FIELD = "COURSE";

    @ExceptionHandler(NotFoundCourseException.class)
    public ResponseEntity<ExceptionDto> notFoundCourseException(NotFoundCourseException exception) {
        log.debug("[NotFoundCourseException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(NotFoundPurchaseHistoryException.class)
    public ResponseEntity<ExceptionDto> notFoundPurchaseHistoryException(NotFoundPurchaseHistoryException exception) {
        log.debug("[NotFoundPurchaseHistoryException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(AlreadyBuyCourseException.class)
    public ResponseEntity<ExceptionDto> alreadyBuyCourseException(AlreadyBuyCourseException exception) {
        log.debug("[AlreadyBuyCourseException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage()).build());
    }
}
