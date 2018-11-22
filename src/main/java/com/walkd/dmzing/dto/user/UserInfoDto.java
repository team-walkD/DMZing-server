package com.walkd.dmzing.dto.user;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {

    private String email;

    private String nick;

    private Long courseCount;

    private Long reviewCount;

    private Long dp;

}
