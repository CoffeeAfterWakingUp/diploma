package com.example.diplomaprojectgeneticcode.dto;


import com.example.diplomaprojectgeneticcode.entity.CourseVideo;
import com.example.diplomaprojectgeneticcode.entity.User;
import lombok.*;

import java.math.BigDecimal;
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
public class CourseDto {
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
    private String category;
    private Set<UserDto> teachers;
}
