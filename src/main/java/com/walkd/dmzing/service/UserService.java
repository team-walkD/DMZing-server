package com.walkd.dmzing.service;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.dp.DpHistoryDto;
import com.walkd.dmzing.dto.exception.LetterDto;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import com.walkd.dmzing.dto.user.UserDpInfoDto;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.UserInfoDto;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    //todo 서비스 분리.
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    private final CourseRepository courseRepository;

    private final DpHistoryRepository dpHistoryRepository;

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    @Transactional
    public UserDetailsImpl create(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) throw new EmailAlreadyExistsException();

        User user = userRepository.save(userDto.toEntity(passwordEncoder));

        purchasedCourseByUserRepository.save(PurchasedCourseByUser.builder()
                .course(courseRepository.findById(Course.DEFAULT_COURSE_ID).orElseThrow(NotFoundCourseException::new))
                .user(user).isPicked(true).build());
        dpHistoryRepository.save(DpHistory.builder().dp(user.getDmzPoint()).dpType(DpHistory.INIT_DP).user(user).build());

        return user.createUserDetails();
    }

    @Transactional(readOnly = true)
    public UserInfoDto showUserInfo(String email) {
//        select (SELECT count(*) FROM dmzing.purchased_course_by_user where purchased_course_by_user.user_id = user.id),
//        (SELECT count(*) FROM dmzing.review where review.user_id = user.id),email,nickname,dmz_point
//        from dmzing.user where user.email = "example@gmail.com"

        return new UserInfoDto(
                userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new),
                purchasedCourseByUserRepository.countByUser_Email(email),
                reviewRepository.countReviewByUser_Email(email));
    }

    @Transactional
    public List<SimpleReviewDto> showUserReview(String email) {
        return reviewRepository.findAllByUser_Email(email)
                .stream()
                .map(review -> new SimpleReviewDto(review,email))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseSimpleDto> showUserCourse(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        return purchasedCourseByUserRepository.findAllByUserId(user.getId())
                .stream()
                .map(purchasedCourseByUser -> purchasedCourseByUser.toCourseSimpleDto())
                .collect(Collectors.toList());
    }
//
//    @Transactional
//    public List<DpHistoryDto> showUserDmzPoint(String email) {
//        return dpHistoryRepository.findAllByUser_Email(email)
//                .stream()
//                .map(dpHistory -> new DpHistoryDto(dpHistory))
//                .collect(Collectors.toList());
//    }


    @Transactional
    public UserDpInfoDto showUserDmzPoint(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        return UserDpInfoDto.builder().dpHistoryDtos(dpHistoryRepository.findAllByUser_Email(email)
                .stream()
                .map(dpHistory -> new DpHistoryDto(dpHistory))
                .collect(Collectors.toList())).totalDp(user.getDmzPoint()).build();
    }

    @Transactional
    public List<LetterDto> showUserMailBox(Long cid, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        Course course = courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new);

        List<MissionHistory> missionHistories = missionHistoryRepository
                .findAllByPurchasedCoursesByUser_CourseAndPurchasedCoursesByUser_User(course, user);

        if (missionHistories == null) return new ArrayList<>();

        return missionHistories.stream()
                .map(missionHistory -> missionHistory.toLetterDto())
                .collect(Collectors.toList());
    }
}
