package com.walkd.dmzing.advice;

import com.walkd.dmzing.dto.user.validation.ValidationExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionDto>> invalidMethodArgument(MethodArgumentNotValidException exception) {
        log.debug("[MethodArgumentNotValidException] validation exception {}", exception.getBindingResult().getAllErrors());
        List<ValidationExceptionDto> validationExceptionDtos = new ArrayList<>();

        exception.getBindingResult().getAllErrors()
                .forEach(validError -> validationExceptionDtos.add(ValidationExceptionDto.toDto(validError)));

        return new ResponseEntity(validationExceptionDtos,HttpStatus.BAD_REQUEST);
    }
}
