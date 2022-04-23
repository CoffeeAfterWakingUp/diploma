package com.example.diplomaprojectgeneticcode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//@JsonInclude(NON_NULL)
public class ResponseDTO<T> {
    private int statusCode;
    private HttpStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;
    private T data;
}
