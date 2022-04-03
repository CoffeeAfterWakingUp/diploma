package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.CourseDto;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.http.Response;
import com.example.diplomaprojectgeneticcode.mapper.CourseMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseRestController {

    private final CourseService courseService;


    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getCourseById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(courseService.getCourseById(id))
                        .build()
        );
    }


    @PostMapping("")
    public ResponseEntity<Response<?>> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(courseService.createCourse(CourseMapper.toCourse(courseDto)))
                        .build()
        );
    }













}
