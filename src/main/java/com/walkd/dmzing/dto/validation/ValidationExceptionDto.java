package com.walkd.dmzing.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;

@Slf4j
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionDto {
    public final static int NAME_PARSE_INDEX = 8;
    public final static int CODES_FIELD_STRING_INDEX = 1;

    private String field;
    private String message;

    public static ValidationExceptionDto toDto(ObjectError validError) {

        return ValidationExceptionDto.builder()
                .message(validError.getDefaultMessage())
                .field(validError.getCodes()[CODES_FIELD_STRING_INDEX]
                        .substring(NAME_PARSE_INDEX)).build();
    }
}
