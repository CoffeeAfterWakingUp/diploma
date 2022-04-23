package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.ContentDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentService {

    private final RestTemplate restTemplate;


    public ContentDTO getContentById(UUID contentId) {
        ResponseDTO<ContentDTO> response = null;
        try {
            String uri = "api/content/{contentId}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<ContentDTO>>() {},
                    contentId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getCoursesOfUser: {}", e.getMessage());
            return new ContentDTO();
        }

        ContentDTO content = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(new ContentDTO());

        log.info("Content: {}", content);
        return content;
    }

    public boolean updateCompletedOfContent(UUID studentId, UUID contentId, boolean isUserCompleted) {
        ResponseDTO<Boolean> response = null;
        try {
            String uri = "api/content/{contentId}?studentId={studentId}&isUserCompleted={isUserCompleted}";


            response = restTemplate.exchange(uri,
                    HttpMethod.PUT,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<Boolean>>() {},
                    contentId, studentId, isUserCompleted).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return false;
        }

        Boolean responseData = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .filter(data -> data)
                .orElse(false);

        log.info("responseData: {}", responseData);
        return responseData;
    }

    public List<ContentDTO> getCompletedContentsOfStudent(UUID studentId, UUID courseId) {
        ResponseDTO<List<ContentDTO>> response = null;
        try {
            String uri = "api/content/{courseId}/{studentId}/contents/completed";


            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<ContentDTO>>>() {},
                    courseId, studentId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<ContentDTO> responseData = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("responseData: {}", responseData);
        return responseData;
    }


    public Map<UUID, String> getCompletedContentsId(UUID studentId, UUID courseId) {
        return getCompletedContentsOfStudent(studentId, courseId).stream()
                .collect(Collectors.toMap(ContentDTO::getId, ContentDTO::getName));
    }







}
