package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@Slf4j
public class CourseService extends SharedService {

    private RestTemplate restTemplate;


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CourseDTO> getTopCourses() {
        ResponseDTO<List<CourseDTO>> response = null;
        try {
            String uri = UriComponentsBuilder.fromUriString("api/courses/top")
                    .toUriString();
            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CourseDTO>>>() {
                    }).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getTopCourses: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CourseDTO> topCourses = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("Top Courses: {}", topCourses);
        return topCourses;
    }


    public List<CourseDTO> filterCoursesByTitle(String title) {
        ResponseDTO<List<CourseDTO>> response = null;
        try {
            String uri = "api/courses/filter?title={title}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CourseDTO>>>() {
                    },
                    title).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in filterCoursesByTitle: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CourseDTO> courses = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("Filtered Courses: {}", courses);
        return courses;
    }


    public List<CourseDTO> getSortedCourses(String[] sort) {
        ResponseDTO<List<CourseDTO>> response = null;
        try {
            String uri = UriComponentsBuilder.fromUriString("api/courses")
                    .queryParam("sort", sort)
                    .toUriString();
            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CourseDTO>>>() {
                    }).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getSortedCourses: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CourseDTO> courses = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("Top Courses: {}", courses);
        return courses;
    }


    public List<CourseDTO> filterByTeacherAndLanguageAndLevelAndCategoryName(List<CourseDTO> courses,
                                                                             String teacherName,
                                                                             String language,
                                                                             String level, String category) {

        List<CourseDTO> resultCourses;
        List<CourseDTO> coursesFilteredByTeacherName = filterByTeacherName(courses, teacherName);
        List<CourseDTO> coursesFilteredByLanguage = filterByLanguage(coursesFilteredByTeacherName, language);
        List<CourseDTO> coursesFilteredByLevel = filterByLevel(coursesFilteredByLanguage, level);
        List<CourseDTO> filteredByCategoryName = filterByCategoryName(coursesFilteredByLevel, category);

        resultCourses = filteredByCategoryName;

        log.info("Filtered by teacher, language, level: {}", resultCourses);

        return resultCourses;
    }


    public List<CourseDTO> filterByTeacherName(List<CourseDTO> courses, String teacherName) {

        if (teacherName.isBlank()) {
            return courses;
        }

        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {
            if (course.getTeachers().contains(teacherName)) {
                resultCourses.add(course);
            }
        }

        log.info("Filtered By teacher name: {}", resultCourses);

        return resultCourses;
    }

    public List<CourseDTO> filterByLanguage(List<CourseDTO> courses, String language) {
        if (language.isBlank()) {
            return courses;
        }

        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {
            if (course.getCourseLang().equals(language)) {
                resultCourses.add(course);
            }
        }

        log.info("Filtered By language: {}", resultCourses);
        return resultCourses;
    }

    public List<CourseDTO> filterByLevel(List<CourseDTO> courses, String level) {
        if (level.isBlank()) {
            return courses;
        }
        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {
            if (course.getCourseLevel().equals(level)) {
                resultCourses.add(course);
            }
        }

        log.info("Filtered By level: {}", resultCourses);

        return resultCourses;
    }


    public List<CourseDTO> filterByCategoryName(List<CourseDTO> courses, String categoryName) {

        if (categoryName.isBlank()) {
            return courses;
        }

        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {
            if (course.getCategory().equals(categoryName)) {
                resultCourses.add(course);
            }
        }

        log.info("Filtered By category: {}", resultCourses);

        return resultCourses;
    }


    public List<CourseDTO> getCoursesOfUser(String username) {
        ResponseDTO<List<CourseDTO>> response = null;
        try {
            String uri = "api/courses/{username}/courses";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<CourseDTO>>>() {
                    },
                    username).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getCoursesOfUser: {}", e.getMessage());
            return Collections.emptyList();
        }

        List<CourseDTO> courses = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(Collections.emptyList());

        log.info("Courses: {}", courses);
        return courses;
    }

    public List<CourseDTO> filterCoursesByTitle(String title, List<CourseDTO> courses) {
        if (title.isBlank()) {
            return courses;
        }
        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {

            if (course.getTitle().toLowerCase().contains(title.toLowerCase())) {
                resultCourses.add(course);
            }
        }
        log.info("Courses: {}", resultCourses);

        return resultCourses;
    }

    public CourseDTO getCourseById(UUID courseId) {
        ResponseDTO<CourseDTO> response = null;
        try {
            String uri = "api/courses/{courseId}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<CourseDTO>>() {
                    },
                    courseId).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception in getCoursesOfUser: {}", e.getMessage());
            return new CourseDTO();
        }

        CourseDTO course = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(new CourseDTO());

        log.info("Course: {}", course);
        return course;
    }


    public void rollInCourse(String username, UUID courseId) {
        ResponseDTO<Boolean> response = null;
        try {
            String uri = "api/courses/{courseId}/rollIn?username={username}";

            response = restTemplate.exchange(uri,
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<Boolean>>() {
                    },
                    courseId, username).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }

        Boolean responseData = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(false);

        log.info("Response Data: {}", responseData);
    }

    public boolean isStudentRolledIn(String username, UUID courseId) {
        ResponseDTO<Boolean> response = null;
        try {
            String uri = "api/courses/{courseId}/isAlreadyRolledIn?username={username}";

            response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<Boolean>>() {
                    },
                    courseId, username).getBody();
            log.info("response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return false;
        }

        Boolean responseData = Optional.ofNullable(response)
                .map(ResponseDTO::getData)
                .orElse(false);

        log.info("Response Data: {}", responseData);
        return responseData;
    }



}
