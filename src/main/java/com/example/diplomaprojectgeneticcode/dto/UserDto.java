package com.example.diplomaprojectgeneticcode.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String name;
    private String surname;
    private String status;
    private String biography;
    private String image;
    private boolean enabled;
    private boolean locked;
}
