package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.dto.ReviewDTO;
import com.example.diplomaprojectgeneticcode.dto.StudentContentDTO;
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
public class ReviewService {

    private final RestTemplate restTemplate;

    public List<ReviewDTO> getReviewsByCourseId(UUID courseId) {
        ResponseDTO<List<ReviewDTO>> response = null;
        try {
            String uri = "api/reviews/{courseId}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<ReviewDTO>>>() {},
                    courseId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<ReviewDTO> responseDTO = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());


        log.info("response data: {}", responseDTO);
        return responseDTO;
    }
}
