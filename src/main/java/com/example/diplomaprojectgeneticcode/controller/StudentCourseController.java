package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.Set;

import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.destroyCartSession;
import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.getCartSession;

@Controller
@RequestMapping("/student-course")
@RequiredArgsConstructor
public class StudentCourseController {

    private final CourseService courseService;


    @GetMapping("/rollIn")
    public ModelAndView rollInCourse(@RequestParam String username, HttpSession session) {
        Set<CourseDTO> cartSession = getCartSession(session);
        for(CourseDTO c : cartSession) {
            courseService.rollInCourse(username, c.getId());
        }

        return new ModelAndView("redirect:/student-content/add?username=" + String.format("%s", username));
    }
}
