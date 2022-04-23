package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.service.client.ContentService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.SectionService;
import com.example.diplomaprojectgeneticcode.service.client.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.COURSE_CURRICULUM_PAGE;

@Controller
@RequestMapping("my-courses")
@RequiredArgsConstructor
public class CourseCurriculumController {

    private final CourseService courseService;
    private final SectionService sectionService;
    private final ContentService contentService;
    private final UserService userService;

    @GetMapping("{courseId}/curriculum")
    public ModelAndView getCourseCurriculumPage(@PathVariable UUID courseId) {
        ModelAndView modelAndView = new ModelAndView(COURSE_CURRICULUM_PAGE);

        CourseDTO course = courseService.getCourseById(courseId);

        List<SectionDTO> sections = sectionService.getSectionsByCourseId(courseId);

        // #TODO change to normal username
        Map<UUID, String> completedContentsIdOfStudent = contentService
                .getCompletedContentsId(userService.getUserByUsername("180103273@stu.sdu.edu.kz").getId(), courseId);

        sections.stream()
                .map(SectionDTO::getContents)
                .flatMap(Collection::stream)
                .filter(c -> completedContentsIdOfStudent.containsKey(c.getId()))
                .forEach(c -> c.setUserCompleted(true));

        modelAndView.addObject("course", course);
        modelAndView.addObject("sections", sections);

        return modelAndView;
    }


}
