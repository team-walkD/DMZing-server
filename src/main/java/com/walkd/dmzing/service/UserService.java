package com.walkd.dmzing.service;

import com.walkd.dmzing.auth.UserDetailsImpl;
import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.PlaceDto;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import com.walkd.dmzing.dto.user.UserDto;
import com.walkd.dmzing.dto.user.UserInfoDto;
import com.walkd.dmzing.dto.user.UserDpInfoDto;
import com.walkd.dmzing.exception.EmailAlreadyExistsException;
import com.walkd.dmzing.exception.NotFoundCourseException;
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
    //todo 서비스 분리.
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    private final CourseRepository courseRepository;

    private final DpHistoryRepository dpHistoryRepository;

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    private final PlaceRepository placeRepository;

    @Transactional
    public UserDetailsImpl create(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) throw new EmailAlreadyExistsException();

        User user = userRepository.save(User.fromDto(userDto, passwordEncoder));

        purchasedCourseByUserRepository.save(PurchasedCourseByUser.builder()
                .course(courseRepository.findById(Course.DEFAULT_COURSE_ID).orElseThrow(NotFoundCourseException::new))
                .user(user).isPicked(true).build());
        dpHistoryRepository.save(DpHistory.builder().dp(user.getDmzPoint()).dpType(DpHistory.INIT_DP).user(user).build());

        return user.createUserDetails();
    }

    @Transactional
    public UserInfoDto showUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

         return user.toUserInfoDto(purchasedCourseByUserRepository.countByUser_Email(email)
                 ,reviewRepository.countReviewByUserId(user.getId()));
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
    public List<CourseSimpleDto> showUserCourse(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        return purchasedCourseByUserRepository.findAllByUserId(user.getId())
                .stream()
                .map(purchasedCourseByUser ->
                        CourseSimpleDto.builder()
                                .title(purchasedCourseByUser.getCourse().getType().getTypeName())
                                .id(purchasedCourseByUser.getCourse().getId())
                                .isPicked(purchasedCourseByUser.getIsPicked())
                                .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDpInfoDto showUserDmzPoint(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        List<DpHistory> dpHistories = dpHistoryRepository.findAllByUserId(user.getId());

        return user.toUserDpDto(dpHistories);
    }

    @Transactional
    public List<PlaceDto> showUserMailBox(Long cid, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        // 유저 인덱스와 코스 인덱스를 통해 구매 테이블에서 구매 목록 인덱스 조회
        List<PurchasedCourseByUser> purchasedCourseByUsers = purchasedCourseByUserRepository.findAllByUserIdAndCourseId(user.getId(), cid);

        // 구매목록 인덱스를 통해 미션히스토리 조회
        List<MissionHistory> missionHistories = purchasedCourseByUsers
                .stream()
                .map(purchasedCourseByUser ->
                        missionHistoryRepository.findAllByPurchasedCoursesByUserId(purchasedCourseByUser.getId()))
                .collect(Collectors.toList());

        // 미션히스토리를 통해 place 조회
        List<Place> places = missionHistories
                .stream()
                .map(missionHistory -> placeRepository.findAllById(missionHistory.getPlace().getId()))
                .collect(Collectors.toList());

        return places.stream().map(place -> place.toPlaceDto(place))
                .collect(Collectors.toList());
    }
}
