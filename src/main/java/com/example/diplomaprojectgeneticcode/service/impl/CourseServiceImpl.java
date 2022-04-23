package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.repo.CourseRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.repo.util.SortDetails.getOrders;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    @Override
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course getCourseById(UUID id) {
        return courseRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getCourses(String[] sort) {
        List<Sort.Order> orders = getOrders(sort);
        List<Course> courses = new ArrayList<>();
        if (orders.isEmpty()) {
            courses = courseRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
            return courses;
        }
        courses = courseRepo.findAll(Sort.by(orders));
        return courses;
    }

    @Override
    public List<Course> getTopCourses() {
        return courseRepo.getTopCourses();
    }

    @Override
    public List<Course> getCoursesByTitleContaining(String title) {
        log.info("Title: {}", title);
        return courseRepo.findAllByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Course> getCoursesByUsername(String username) {
        log.info("username: {}", username);
        return courseRepo.getCoursesByUser(username);
    }
}
