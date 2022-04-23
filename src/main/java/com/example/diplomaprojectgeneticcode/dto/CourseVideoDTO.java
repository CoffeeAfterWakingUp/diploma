package com.example.diplomaprojectgeneticcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseVideoDTO {

    private UUID id;
    private String videoUrl;
    private String title;
    private Integer duration;

}
