package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.dto.course.CourseMainDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByType(Type type);
}
