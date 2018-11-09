package com.walkd.dmzing.service;

import com.walkd.dmzing.dto.course.CourseDetailResponseDto;
import com.walkd.dmzing.dto.course.CourseMainResponseDto;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    @Transactional(readOnly = true)
    public List<CourseMainResponseDto> showCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDetailResponseDto> showCourseDetail() {

        return null;
    }

}
