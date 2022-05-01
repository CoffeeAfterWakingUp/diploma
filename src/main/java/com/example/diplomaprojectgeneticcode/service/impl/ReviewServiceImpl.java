package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.Review;
import com.example.diplomaprojectgeneticcode.repo.ReviewRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import com.example.diplomaprojectgeneticcode.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final CourseService courseService;

    @Override
    public List<Review> getReviewsByCourseId(UUID courseId) {
        return reviewRepo.findAllByCourse(courseService.getCourseById(courseId), Sort.by(Sort.Direction.DESC, "postDate"));
    }

    @Override
    public Double getAvgRatingOfCourse(UUID courseId) {
        return reviewRepo.getAvgRatingOfCourse(courseId);
    }

    @Override
    public long getCountOfReviewsOfCourse(UUID courseId) {
        return reviewRepo.getCountOfReviewsOfCourse(courseId);
    }
}
