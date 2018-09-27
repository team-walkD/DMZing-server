package com.walkd.dmzing.dto.user.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationExceptionDto {
    private String field;
    private String message;

    public static ValidationExceptionDto toDto(ObjectError validError) {
        //todo  field 값 파싱
        return ValidationExceptionDto.builder()
                .message(validError.getDefaultMessage())
                .field(null).build();
    }
}
