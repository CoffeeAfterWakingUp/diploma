package com.example.diplomaprojectgeneticcode.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {
    private String subject;
    private String recipient;
    private String body;
}
