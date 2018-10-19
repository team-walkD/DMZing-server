package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.exception.ExceptionDto;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserRestControllerAdvice {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity emailAlreadyExists(EmailAlreadyExistsException exception) {
        log.debug("[EmailAlreadyExistsException] emailAlreadyExists exception {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
