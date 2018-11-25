package com.walkd.dmzing.dto.user;


import com.walkd.dmzing.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {

    private String email;

    private String nick;

    private Long courseCount;

    private Long reviewCount;

    private Long dp;

    public UserInfoDto(User user,Long courseCount,Long reviewCount) {
        this.email = user.getEmail();
        this.nick = user.getNickname();
        this.courseCount = courseCount;
        this.reviewCount = reviewCount;
        this.dp = user.getDmzPoint();
    }
}
