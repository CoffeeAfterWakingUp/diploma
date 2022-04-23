package com.example.diplomaprojectgeneticcode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {

    private UUID id;
    private String name;
    private String type;
    private String sectionName;
    private UUID sectionId;
    private String courseName;
    private UUID courseId;
    private String contentUrl;
    private boolean enabled;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private boolean isUserCompleted;
    private UUID studentId;


}
