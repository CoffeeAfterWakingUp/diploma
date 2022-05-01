package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ReviewDTO;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ReviewMapper {

    public ReviewDTO toDto(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        Optional<Review> reviewOpt = Optional.ofNullable(review);

        reviewOpt.map(Review::getId).ifPresent(reviewDTO::setId);
        reviewOpt.map(Review::getMessage).ifPresent(reviewDTO::setMessage);
        reviewOpt.map(Review::getRating).ifPresent(reviewDTO::setRating);
        reviewOpt.map(Review::getUser).ifPresent(user -> {
            reviewDTO.setUserId(user.getId());
            reviewDTO.setUserFullName(user.getFullName());
        });
        reviewOpt.map(Review::getCourse).ifPresent(course -> reviewDTO.setCourseId(course.getId()));
        reviewOpt.map(Review::getPostDate).ifPresent(postDate -> reviewDTO.setPostDate(postDate.toLocalDate()));

        return reviewDTO;
    }

    public List<ReviewDTO> toDto(List<Review> reviews) {
        return Optional.ofNullable(reviews)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
