package com.walkd.dmzing.domain;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.UserInfoDto;
import com.walkd.dmzing.dto.user.UserDpInfoDto;
import lombok.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String nickname;
    private String authority;
    private String phoneNumber;

    private Long dmzPoint = 500L;


    @Builder
    public User(String email, String password, String nickname, String authority, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.phoneNumber = phoneNumber;
    }

    public UserDetailsImpl createUserDetails() {
        return new UserDetailsImpl(email, password, AuthorityUtils.createAuthorityList(authority));
    }

    public void buyCourse(Course course) {
        this.dmzPoint = course.isEnoughMoney(this.dmzPoint);
    }

    public UserInfoDto toUserInfoDto(Long courseCount, Long reviewCount) {
        return UserInfoDto.builder()
                .email(this.email)
                .nick(nickname)
                .dp(dmzPoint)
                .reviewCount(reviewCount)
                .courseCount(courseCount)
                .build();
    }

    public UserDpInfoDto toUserDpDto(List<DpHistory> dpHistories) {
        return UserDpInfoDto.builder()
                .totalDp(dmzPoint)
                .dpHistoryDtos(dpHistories.stream().map(dpHistory -> dpHistory.toDto()).collect(Collectors.toList()))
                .build();
    }

    public static User fromDto(UserDto userDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .phoneNumber(userDto.getPhoneNumber())
                .authority(UserDto.USER_AUTHORITY).build();
    }

}
