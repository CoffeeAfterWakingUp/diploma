package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<Review> getReviewsByCourseId(UUID courseId);
    Double getAvgRatingOfCourse(UUID courseId);
    long getCountOfReviewsOfCourse(UUID courseId);
}
