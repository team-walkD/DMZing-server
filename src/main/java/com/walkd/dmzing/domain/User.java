package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.UserDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private Boolean authority;
    private String phoneNumber;


    @Builder
    public User(String email, String password, String nickname, Boolean authority, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.phoneNumber = phoneNumber;
    }

    public static User fromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .phoneNumber(userDto.getPhoneNumber())
                .authority(false).build();
    }
}
