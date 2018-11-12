package com.walkd.dmzing.service;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.domain.DpHistory;
import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.info.UserDpInfoDto;
import com.walkd.dmzing.dto.user.info.UserInfoDto;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    private final CourseRepository courseRepository;

    private final DpHistoryRepository dpHistoryRepository;
    
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

    @Transactional
    public List<SimpleReviewDto> showUserReview(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        return reviewRepository.findAllByUserId(user.getId())
                .stream()
                .map(review -> review.toSimpleDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void showUserCourse(String email) {

    }

    @Transactional
    public UserDpInfoDto showUserDmzPoint(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        List<DpHistory> dpHistories = dpHistoryRepository.findAllByUserId(user.getId());

        // TODO dto 수정
        UserDpInfoDto userDpInfoDto = new UserDpInfoDto(user.getDmzPoint(), dpHistories);
        return userDpInfoDto;
    }
}
