package com.walkd.dmzing.controller;

import com.walkd.dmzing.dto.course.CourseDto;
import com.walkd.dmzing.dto.course.CourseMainResponseDto;
import com.walkd.dmzing.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<CourseMainResponseDto>> showCourses(){

        return ResponseEntity.ok().body(courseService.showCourses());
    }

}
