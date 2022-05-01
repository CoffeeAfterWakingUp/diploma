package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Controller
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {
    @GetMapping
    public ModelAndView getNewCoursePage(HttpSession session) {
        String userSession = (String) session.getAttribute(USER_SESSION);
        ModelAndView modelAndView = new ModelAndView(NEWS_PAGE);

//        if (userSession == null) {
//            modelAndView = new ModelAndView("redirect:/" + SIGN_IN_PAGE);
//            return modelAndView;
//        }

        return modelAndView;
    }
}
