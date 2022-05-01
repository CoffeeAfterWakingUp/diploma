package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {


    @Query("SELECT c FROM Course c " +
            "LEFT JOIN c.reviews r " +
            "GROUP BY c.id " +
            "ORDER BY c.students.size DESC, AVG(r.rating) DESC, COUNT(r.id) DESC, c.updatedAt DESC, c.createdAt DESC")
    List<Course> getTopCourses();


    List<Course> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT c FROM Course c " +
            "LEFT JOIN c.students s " +
            "WHERE s.student.email=:username " +
            "ORDER BY s.rolledIn DESC")
    List<Course> getCoursesByUser(String username);










}
