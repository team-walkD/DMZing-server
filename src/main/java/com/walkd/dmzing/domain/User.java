package com.walkd.dmzing.domain;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.dto.user.UserDto;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

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

    public static User fromDto(UserDto userDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .phoneNumber(userDto.getPhoneNumber())
                .authority(UserDto.USER_AUTHORITY).build();
    }

    public UserDetailsImpl createUserDetails() {
        return new UserDetailsImpl(email, password, AuthorityUtils.createAuthorityList(authority));
    }

    public void buyCourse(Course course) {
        this.dmzPoint = course.isEnoughMoney(this.dmzPoint);
    }
}
