package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.exception.AlreadySuccessedException;
import com.walkd.dmzing.exception.NotFoundCourseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MissionExceptionControllerAdvice {
    private static final String FIELD = "MISSION";

    @ExceptionHandler(AlreadySuccessedException.class)
    public ResponseEntity<ExceptionDto> notFoundCourseException(AlreadySuccessedException exception) {
        log.debug("[NotFoundCourseException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage()).build());
    }
}
