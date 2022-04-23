package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

/**
 * Controller contains most used methods, for nonduplication
 *
 * @author Olzhas Syrbek
 */
public class AbstractRestController {

    protected <T> ResponseEntity<ResponseDTO<T>> success(T data, HttpStatus status) {
        ResponseDTO<T> responseDto = new ResponseDTO<>(
                status.value(),
                status,
                LocalDateTime.now(),
                SUCCESS,
                data
        );
        return new ResponseEntity<>(responseDto, OK);
    }

    protected <T> ResponseEntity<ResponseDTO<T>> successOK(T data) {
        return success(data, OK);
    }
}
