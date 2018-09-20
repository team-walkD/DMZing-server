package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.UserDto;
import com.walkd.dmzing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(UserDto userDto) {
        userRepository.save(User.fromDto(userDto));
    }

    public void login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).get();
    }
}
