package com.walkd.dmzing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

    public UserDto erasePassword(){
        password = null;
        return this;
    }
}
