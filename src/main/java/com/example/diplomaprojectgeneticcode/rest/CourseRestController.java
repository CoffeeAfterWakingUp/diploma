package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.PaginatedResponse;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.mapper.CourseMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/courses", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CourseRestController extends AbstractRestController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CourseDTO>> getCourseById(@PathVariable UUID id) {
        return successOK(courseMapper.toDto(courseService.getCourseById(id)));
    }


    @PostMapping("")
    public ResponseEntity<ResponseDTO<CourseDTO>> createCourse(@RequestBody CourseDTO courseDto) throws Exception {
        return success(
                courseMapper.toDto(courseService.createCourse(courseMapper.toCourse(courseDto))),
                CREATED);
    }


    @GetMapping()
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> getCourses(@RequestParam(required = false) String[] sort) {
        return successOK(courseMapper.toDto(courseService.getCourses(sort)));
    }

    @GetMapping(value = "/top")
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> getTopCourses() {
        return successOK(courseMapper.toDto(courseService.getTopCourses()));
    }

    @GetMapping("/filter")
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> getCoursesByTitleContaining(@RequestParam String title) {
        return successOK(courseMapper.toDto(courseService.getCoursesByTitleContaining(title)));
    }

    @GetMapping("/{username}/courses")
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> getCoursesByUsername(@PathVariable String username) {
        return successOK(courseMapper.toDto(courseService.getCoursesByUsername(username)));
    }



}
