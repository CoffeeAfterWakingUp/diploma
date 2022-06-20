package com.example.diplomaprojectgeneticcode.dto;


import com.example.diplomaprojectgeneticcode.entity.CourseVideo;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private UUID id;
    private String title;
    private String subtitle;
    private String description;
    private String image;
    private String promoVideo;
    private String benefits;
    private String requirements;
    private String courseLevel;
    private String courseLang;
    private String welcomeMsg;
    private String congratsMsg;
    private BigDecimal price;
    private String status;
    private String rules;
    private String category;
    private Integer absenceLimit;
    private String currency;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private double rating;

    private int reviewCount;

    private int studentCount;

    private Set<String> students;

    private Set<String> teachers;
}
