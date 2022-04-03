package com.example.diplomaprojectgeneticcode.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}
