package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseMainDto;
import com.walkd.dmzing.dto.course.CourseStatusDto;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
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

    @Transactional(readOnly = true)
    public List<CourseMainDto> showCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseMainDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDetailDto> showCourseDetail() {

        return null;
    }

    public CourseStatusDto showCourseStatus(Type type, String email) {
        Long pickCount = purchasedCourseByUserRepository.countByCourse_TypeAndIsPickedTrue(type);
        Boolean isPurchased = purchasedCourseByUserRepository.existsByCourse_TypeAndUser_Email(type, email);
            return CourseStatusDto.builder()
                    .pickCount(pickCount)
                    .isPurchased(isPurchased)
                    .typeName(type.getTypeName())
                    .mainDescription(courseRepository.findByType(type).getMainDescription())
                    .build();
    }

}
