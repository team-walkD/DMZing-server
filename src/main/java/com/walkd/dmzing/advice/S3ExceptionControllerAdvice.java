package com.walkd.dmzing.advice;

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

    @ExceptionHandler(AlreadySaveImageException.class)
    public ResponseEntity alreadySaveImageException(AlreadySaveImageException exception) {
        log.debug("[AlreadySaveImageException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(BadImageUrlException.class)
    public ResponseEntity badImageUrlException(BadImageUrlException exception) {
        log.debug("[BadImageUrlException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
