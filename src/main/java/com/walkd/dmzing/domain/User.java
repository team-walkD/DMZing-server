package com.walkd.dmzing.domain;

import com.walkd.dmzing.auth.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;

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

    public UserDetailsImpl createUserDetails() {
        return new UserDetailsImpl(email, password, AuthorityUtils.createAuthorityList(authority));
    }

    public void buyCourse(Course course) {
        this.dmzPoint = course.isEnoughMoney(this.dmzPoint);
    }

    public void addDmzPoint(Long reward) {
        dmzPoint += reward;
    }
}
