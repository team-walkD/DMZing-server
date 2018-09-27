package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.user.JoinUser;
import com.walkd.dmzing.dto.user.LoginUser;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Void> create(@Validated(JoinUser.class) @RequestBody UserDto userDto) {
        // TODO: 2018. 9. 20. jwt생성해서 자동로그인
        userService.create(userDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Validated(LoginUser.class) @RequestBody UserDto userDto) {
        userService.login(userDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
