package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.exception.AlreadySaveImageException;
import com.walkd.dmzing.exception.BadImageUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class S3ExceptionControllerAdvice {
    private static final String FIELD = "IMAGE";

    @ExceptionHandler(AlreadySaveImageException.class)
    public ResponseEntity<ExceptionDto> alreadySaveImageException(AlreadySaveImageException exception) {
        log.debug("[AlreadySaveImageException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(BadImageUrlException.class)
    public ResponseEntity<ExceptionDto> badImageUrlException(BadImageUrlException exception) {
        log.debug("[BadImageUrlException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage())
                        .build());
    }
}
