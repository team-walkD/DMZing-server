package com.walkd.dmzing.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private final static String EMAIL_REGEX = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+[.[_0-9a-zA-Z-]+]*$";
    private final static String PASSWORD_REGEX = "^(?=.*?[a-zA-Z])(?=.*?[0-9]).{8,12}$";
    private final static String PHONE_NO_REGEX = "^01[0|1|6-9]-[0-9]{3,4}-[0-9]{4}$";

    @NotEmpty(groups = LoginUser.class)
    @Pattern(regexp = EMAIL_REGEX, message = "email 형식이 올바르지 않습니다." , groups = JoinUser.class)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(groups = LoginUser.class)
    @Pattern(regexp = PASSWORD_REGEX, message = "password형식이 올바르지 않습니다.", groups = JoinUser.class)
    private String password;

    @Size(min = 1, max = 20, message = "20자 이하의 닉네임을 입력해 주세요.", groups = JoinUser.class)
    private String nickname;

    @Pattern(regexp = PHONE_NO_REGEX,message = "잘못된 전화번호 형식입니다.", groups = JoinUser.class)
    private String phoneNumber;
}
