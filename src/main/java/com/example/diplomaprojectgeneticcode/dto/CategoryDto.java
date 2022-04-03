package com.example.diplomaprojectgeneticcode.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private Integer parentId;
}