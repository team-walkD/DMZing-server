package com.walkd.dmzing.service;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsImpl create(UserDto userDto) {
       return userRepository.save(User.fromDto(userDto,passwordEncoder)).createUserDetails();
    }

    public void login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).get();
    }
}
