package com.example.diplomaprojectgeneticcode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private UUID id;
    private String message;
    private Integer rating;
    private UUID userId;
    private String userFullName;
    private UUID courseId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postDate;
}
