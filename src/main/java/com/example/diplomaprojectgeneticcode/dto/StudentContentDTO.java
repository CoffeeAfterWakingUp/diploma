package com.example.diplomaprojectgeneticcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentContentDTO {

    private UserDTO student;
    private ContentDTO content;
    private CourseDTO course;
    private String teacher;
    private boolean completed;
    private int grade;
    private String feedback;
    private int calculatedWeight;
    private int minRange;
    private int maxRange;
    private int percentage;
    private Boolean attended;
}
