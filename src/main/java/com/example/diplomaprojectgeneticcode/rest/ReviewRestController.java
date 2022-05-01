package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.dto.ReviewDTO;
import com.example.diplomaprojectgeneticcode.mapper.ReviewMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewRestController extends AbstractRestController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{courseId}")
    public ResponseEntity<ResponseDTO<List<ReviewDTO>>> getReviewsOfCourse(@PathVariable UUID courseId) {
        return successOK(
               reviewMapper.toDto(reviewService.getReviewsByCourseId(courseId))
        );
    }
}
