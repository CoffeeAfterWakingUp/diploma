package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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


    @Query("SELECT c FROM Course c " +
            "LEFT JOIN c.teachers s " +
            "WHERE s.email=:username")
    List<Course> getCoursesByTeacher(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Course c " +
            "SET c.title=:title, " +
            "c.subtitle=:subtitle, " +
            "c.description=:description, " +
            "c.courseLang=:courseLang, " +
            "c.courseLevel=:courseLevel, " +
            "c.category=:category, " +
            "c.image=:imageUrl, " +
            "c.promoVideoUrl=:videoUrl," +
            "c.currency=:currency, " +
            "c.price=:price " +
            "WHERE c.id=:id")
    void updateCourseBasics(String title,
                            String subtitle,
                            String description,
                            String courseLang,
                            CourseLevel courseLevel,
                            Category category,
                            String imageUrl,
                            String videoUrl,
                            UUID id,
                            String currency,
                            BigDecimal price);










}
