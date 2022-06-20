package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.dto.CourseBasicsDTO;
import com.example.diplomaprojectgeneticcode.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course createCourse(Course course) throws Exception;
    Course getCourseById(UUID id);
    List<Course> getCourses(String[] sort);
    List<Course> getTopCourses();
    List<Course> getCoursesByTitleContaining(String title);
    List<Course> getCoursesByUsername(String username);
    boolean rollInCourse(String username, UUID courseId);

    boolean checkIsStudentRolledIn(String username, UUID courseId);

    List<Course> getCoursesByTeacher(String username);

    boolean updateCourseBasics(UUID id, CourseBasicsDTO dto);

}
