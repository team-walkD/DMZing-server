package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.DpHistory;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    private final DpHistoryRepository dpHistoryRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CourseMainDto> showCourses(String email) {
        return courseRepository.findAll()
                .stream()
                .map(course -> course.toCourseMainDto(
                        purchasedCourseByUserRepository.countByCourse_TypeAndIsPickedTrue(course.getType())
                        , purchasedCourseByUserRepository.existsByCourse_TypeAndUser_Email(course.getType(), email)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDetailDto showCourseDetail(Long cid, String email) {
        if (purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).isPresent()) {
            return courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new)
                    .toCourseDetailDto();
        }

        throw new NotFoundCourseException();
    }

    @Transactional
    public void buyCourse(Long cid, String email) {
        //todo 커스텀 익셉션 생성
        //todo QueryDSL을 활용한 한방쿼리 작성
        if(purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid,email).isPresent()) throw new RuntimeException();

        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);

        Course course = courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new);
        user.buyCourse(course);

        purchasedCourseByUserRepository.save(PurchasedCourseByUser.builder().course(course).user(user).isPicked(false).build());
        dpHistoryRepository.save(DpHistory.builder().dpType(course.getType().getTypeName()).user(user).dp(course.getPrice()).build());
    }
}
