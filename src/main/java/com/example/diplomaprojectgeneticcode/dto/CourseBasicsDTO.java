package com.example.diplomaprojectgeneticcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseBasicsDTO {
    private String courseTitle;
    private String courseSubtitle;
    private String courseDescription;
    private String courseLang;
    private String courseLevel;
    private String courseCategory;
    private String imageUrl;
    private String videoUrl;
    private String currency;
    private BigDecimal price;


}
