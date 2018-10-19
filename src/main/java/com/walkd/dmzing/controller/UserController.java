package com.walkd.dmzing.controller;

import com.walkd.dmzing.auth.jwt.JwtInfo;
import com.walkd.dmzing.dto.user.JoinUser;
import com.walkd.dmzing.dto.user.LoginUser;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.service.UserService;
import com.walkd.dmzing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity create(@Validated(JoinUser.class) @RequestBody UserDto userDto) {
        String token = JwtUtil.createToken(userService.create(userDto));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtInfo.HEADER_NAME,token);

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }
}
