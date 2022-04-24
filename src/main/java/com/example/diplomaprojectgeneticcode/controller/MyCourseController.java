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
    @RequestMapping("my-course")
    @RequiredArgsConstructor
    public class MyCourseController {

        private final CourseService courseService;

        @ModelAttribute
        public void setAttributes(Model model) {
            model.addAttribute("page", "My Course");
        }

}

