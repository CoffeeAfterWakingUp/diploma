package com.example.diplomaprojectgeneticcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDTO {
    private String name;
    private String surname;
    private String email;
    private String image;
    private String socialMediaUrl;
    private String personalWebUrl;
    private String biography;
    private String phoneNumber;

}
