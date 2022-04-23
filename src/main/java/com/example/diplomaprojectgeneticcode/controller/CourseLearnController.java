package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.ContentDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.dto.UserDTO;
import com.example.diplomaprojectgeneticcode.service.client.ContentService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.SectionService;
import com.example.diplomaprojectgeneticcode.service.client.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Controller
@RequestMapping("my-courses")
@RequiredArgsConstructor
public class CourseLearnController {

    private final CourseService courseService;
    private final SectionService sectionService;
    private final ContentService contentService;
    private final UserService userService;

    @GetMapping("{courseId}/curriculum/learn/{contentId}")
    public ModelAndView getCourseCurriculumDashboardPage(@PathVariable UUID courseId,
                                                         @PathVariable UUID contentId,
                                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(COURSE_CURRICULUM_DASHBOARD_PAGE);

        String username = (String) session.getAttribute(USER_SESSION);
        if (username == null) {
            return new ModelAndView("redirect:/" + SIGN_IN_PAGE);
        }

        CourseDTO course = courseService.getCourseById(courseId);
        List<SectionDTO> sections = sectionService.getSectionsByCourseId(courseId);
        ContentDTO content = contentService.getContentById(contentId);
        Map<UUID, String> completedContentsIdOfStudent = contentService
                .getCompletedContentsId(userService.getUserByUsername(username).getId(), courseId);

        sections.stream()
                .map(SectionDTO::getContents)
                .flatMap(Collection::stream)
                .filter(c -> completedContentsIdOfStudent.containsKey(c.getId()))
                .forEach(c -> c.setUserCompleted(true));

        if(completedContentsIdOfStudent.containsKey(content.getId())) {
            content.setUserCompleted(true);
        }

        modelAndView.addObject("course", course);
        modelAndView.addObject("sections", sections);
        modelAndView.addObject("content", content);

        return modelAndView;
    }


    @GetMapping("{courseId}/{contentId}")
    public ModelAndView updateIsCompletedOfContent(@PathVariable UUID courseId,
                                                   @PathVariable UUID contentId,
                                                   @RequestParam boolean isCompleted,
                                                   HttpSession session) {

        String username = (String) session.getAttribute(USER_SESSION);
        if (username == null) {
            return new ModelAndView("redirect:/" + SIGN_IN_PAGE);
        }

        UserDTO user = userService.getUserByUsername(username);

        contentService.updateCompletedOfContent(user.getId(), contentId, isCompleted);
        return new ModelAndView("redirect:/" + String.format("my-courses/%s/curriculum/learn/%s", courseId, contentId));
    }
}
