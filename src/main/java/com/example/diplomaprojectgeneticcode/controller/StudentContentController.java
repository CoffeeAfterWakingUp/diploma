package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.ContentService;
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
@RequestMapping("/student-content")
@RequiredArgsConstructor
public class StudentContentController {

    private final ContentService contentService;

    @GetMapping("/add")
    public ModelAndView addContentsToStudent(@RequestParam String username, HttpSession session) {
        Set<CourseDTO> cartSession = getCartSession(session);

        for (CourseDTO c : cartSession) {
            contentService.addContentsToStudent(username, c.getId());
        }

        destroyCartSession(session);

        return new ModelAndView("redirect:/dashboard?completePaymentSuccess=true");
    }
}
