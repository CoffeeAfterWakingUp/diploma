package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.CategoryDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CategoryService extends SharedService {

    private RestTemplate restTemplate;


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CategoryDTO> getPopularCategories() {
        ResponseDTO<List<CategoryDTO>> response = null;
        try{
            String uri = UriComponentsBuilder.fromUriString("api/categories/popular")
                    .toUriString();
            response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CategoryDTO>>>() {
                    }).getBody();
            log.info("Response: {}", response);

        }catch (Exception e) {
            log.error("Exception in getPopularCategories: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CategoryDTO> popularCategories = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("Popular Categories: {}", popularCategories);

        return popularCategories;
    }


    public List<CategoryDTO> getCategories() {
        ResponseDTO<List<CategoryDTO>> response = null;
        try{
            String uri = UriComponentsBuilder.fromUriString("api/categories").toUriString();
            response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CategoryDTO>>>() {
                    }).getBody();
            log.info("Response: {}", response);

        }catch (Exception e) {
            log.error("Exception in getCategories: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CategoryDTO> categories = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("categories: {}", categories);

        return categories;
    }





}
