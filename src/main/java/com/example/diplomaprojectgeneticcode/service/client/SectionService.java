package com.example.diplomaprojectgeneticcode.service.client;


import com.example.diplomaprojectgeneticcode.dto.ContentDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.util.SortContentByCreatedAt;
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
public class SectionService {

    private final RestTemplate restTemplate;

    public List<SectionDTO> getSectionsByCourseId(UUID courseId) {
        ResponseDTO<List<SectionDTO>> response = null;
        try {
            String uri = "api/section/{courseId}/sections";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<SectionDTO>>>() {},
                    courseId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getSectionsByCourseId: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<SectionDTO> sections = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());


        log.info("Sections: {}", sections);
        return sections;
    }

}
