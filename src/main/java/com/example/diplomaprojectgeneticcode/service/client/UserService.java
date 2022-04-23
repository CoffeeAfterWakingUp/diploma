package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.dto.StudentContentDTO;
import com.example.diplomaprojectgeneticcode.dto.UserDTO;
import com.example.diplomaprojectgeneticcode.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;


    public UserDTO getUserByUsername(String username) {
        ResponseDTO<UserDTO> response = null;
        try {
            String uri = "api/users/username/{username}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {},
                    username).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getUserByUsername: {}", e.getMessage());
            return new UserDTO();
        }

        UserDTO userDTO = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(new UserDTO());


        log.info("userDTO: {}", userDTO);
        return userDTO;
    }


    public List<StudentContentDTO> getGradesOfStudent(UUID studentId, UUID courseId) {
        ResponseDTO<List<StudentContentDTO>> response = null;
        try {
            String uri = "api/users/{studentId}/{courseId}/grades";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<StudentContentDTO>>>() {},
                    studentId, courseId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<StudentContentDTO> userDTO = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());


        log.info("response data: {}", userDTO);
        return userDTO;
    }

    public List<StudentContentDTO> getAttendanceOfStudent(UUID studentId, UUID courseId) {
        ResponseDTO<List<StudentContentDTO>> response = null;
        try {
            String uri = "api/users/{studentId}/{courseId}/attendance";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<StudentContentDTO>>>() {},
                    studentId, courseId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<StudentContentDTO> userDTO = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());


        log.info("response data: {}", userDTO);
        return userDTO;
    }



}
