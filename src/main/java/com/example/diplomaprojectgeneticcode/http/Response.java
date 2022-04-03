package com.example.diplomaprojectgeneticcode.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response<T> {
    private int statusCode;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private T data;
}
