package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.course.PlaceDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundPurchaseHistoryException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.MissionHistoryRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
import com.walkd.dmzing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CourseMainDto> showCourses(String email) {

        //todo: 픽 수 중복??????
        return courseRepository.findAll()
                .stream()
                .map(course -> course.toCourseMainDto(
                        purchasedCourseByUserRepository.countByCourse_TypeAndIsPickedTrue(course.getType())
                        , purchasedCourseByUserRepository.existsByCourse_TypeAndUser_Email(course.getType(), email)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDetailDto showCourseDetail(Long cid, String email) {

        if (purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).isPresent())
            return courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new)
                    .toCourseDetailDto();

        throw new NotFoundCourseException();
    }

    @Transactional
    public CourseDetailDto pickCourse(Long cid, String email) {
        List<PurchasedCourseByUser> purchasedCourseList = purchasedCourseByUserRepository.findByUser_Email(email);
        PurchasedCourseByUser purchasedCourse = purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).orElseThrow(NotFoundCourseException::new);

        if(!purchasedCourseList.isEmpty()) {
            purchasedCourseList
                    .stream()
                    .forEach(purchasedCourseByUser -> purchasedCourseByUser.setPicked(Boolean.FALSE));
            purchasedCourse.setPicked(true);
            // todo: 어디까지 성공 했는지 인자값 넘겨야 함.
            return purchasedCourse.getCourse().toCourseDetailDto();
        } else {
            throw new NotFoundPurchaseHistoryException();
        }

    }

    public List<PlaceDto> showPlacesInCourse(Long cid) {
        return courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new)
                .getPlaces()
                .stream()
                .map(place -> place.toPlaceDto())
                .collect(Collectors.toList());
    }
}
