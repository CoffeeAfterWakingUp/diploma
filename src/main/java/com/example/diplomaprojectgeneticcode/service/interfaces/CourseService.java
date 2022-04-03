package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.dto.CourseDto;
import com.example.diplomaprojectgeneticcode.entity.Course;

import java.util.UUID;

public interface CourseService {
    Course createCourse(Course course);
    CourseDto getCourseById(UUID id);
}
