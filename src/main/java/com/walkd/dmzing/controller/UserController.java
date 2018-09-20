package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.UserDto;
import com.walkd.dmzing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody UserDto userDto){
        // TODO: 2018. 9. 20. 유저 비밀번호 암호화, 정규식 검사, jwt생성해서 자동로그인
        userService.create(userDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto userDto){
        userService.login(userDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
