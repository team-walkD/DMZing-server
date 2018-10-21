package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
