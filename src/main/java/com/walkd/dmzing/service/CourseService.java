package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.MissionHistoryRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
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

        if (purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).isPresent())
            return courseRepository.findById(cid).orElseThrow(NotFoundCourseException::new)
                    .toCourseDetailDto();

        throw new NotFoundCourseException();
    }


}
