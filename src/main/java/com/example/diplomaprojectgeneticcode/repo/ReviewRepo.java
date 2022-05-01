package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReviewRepo extends JpaRepository<Review, UUID> {

    List<Review> findAllByCourse(Course course, Sort sort);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.course.id=:courseId")
    Double getAvgRatingOfCourse(UUID courseId);

    @Query("SELECT count(r) FROM Review r WHERE r.course.id=:courseId")
    long getCountOfReviewsOfCourse(UUID courseId);


}
