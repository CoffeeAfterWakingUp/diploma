package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.CourseDto;
import com.example.diplomaprojectgeneticcode.dto.UserDto;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.repo.CourseRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    @Override
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public CourseDto getCourseById(UUID id) {
        Course course = courseRepo.findById(id).orElseThrow();
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .subtitle(course.getSubtitle())
                .description(course.getDescription())
                .courseLang(course.getCourseLang())
                .category(course.getCategory().getName())
                .teachers(course.getTeachers()
                        .stream().map(c -> new UserDto())
                        .collect(Collectors.toSet()))
                .build();

    }


}
