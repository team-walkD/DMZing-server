package com.walkd.dmzing.service;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.domain.Review;
import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.UserInfoDto;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.ReviewLikeRepository;
import com.walkd.dmzing.repository.ReviewRepository;
import com.walkd.dmzing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    private final CourseRepository courseRepository;

    public UserDetailsImpl create(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) throw new EmailAlreadyExistsException();

        return userRepository.save(User.fromDto(userDto, passwordEncoder)).createUserDetails();
    }

    @Transactional
    public UserInfoDto showUserInfo(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        Long writtenReviewCount = reviewRepository.countReviewByUserId(user.getId());
        Long likedReviewCount = reviewLikeRepository.countReviewLikesByUserId(user.getId());

        Long reviewCount = writtenReviewCount + likedReviewCount;

        // TODO 유저가 좋아요 누른건지 유저가 픽한건지 확인 필요
        Long likedCourseCount = Long.parseLong("0");

        // TODO dto 수정
        UserInfoDto userInfoDto = new UserInfoDto(user.getEmail(), user.getNickname(), likedCourseCount, reviewCount, user.getDmzPoint());
        return userInfoDto;
    }
}
