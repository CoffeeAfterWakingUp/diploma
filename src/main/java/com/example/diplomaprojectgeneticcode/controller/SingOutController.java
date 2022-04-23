package com.example.diplomaprojectgeneticcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.example.diplomaprojectgeneticcode.util.Constant.SIGN_IN_PAGE;
import static com.example.diplomaprojectgeneticcode.util.Constant.USER_SESSION;

@Controller
@RequestMapping("/signout")
public class SingOutController {

    @GetMapping
    public ModelAndView signOut(HttpSession session) {
        session.setAttribute(USER_SESSION, null);

        return new ModelAndView("redirect:/" + SIGN_IN_PAGE);
    }

}
