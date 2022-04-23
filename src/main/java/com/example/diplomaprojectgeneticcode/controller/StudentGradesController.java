package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.StudentContentDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.STUDENT_GRADES_PAGE;
import static com.example.diplomaprojectgeneticcode.util.Constant.USER_SESSION;

@Controller
@RequestMapping("my-courses")
@RequiredArgsConstructor
public class StudentGradesController {

    private final CourseService courseService;
    private final UserService userService;

    @ModelAttribute
    public void setAttributes(Model model) {
        model.addAttribute("page", "grades");
    }

    @GetMapping("{courseId}/grades")
    public ModelAndView getStudentGradesPage(@PathVariable UUID courseId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(STUDENT_GRADES_PAGE);
        String username = (String) session.getAttribute(USER_SESSION);

        // #TODO do checking

        CourseDTO course = courseService.getCourseById(courseId);
        List<StudentContentDTO> grades = userService.getGradesOfStudent(userService.getUserByUsername("180103273@stu.sdu.edu.kz").getId(), courseId);

        modelAndView.addObject("course", course);
        modelAndView.addObject("grades", grades);

        return modelAndView;
    }
}
