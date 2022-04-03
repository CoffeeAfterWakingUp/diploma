package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;

@Controller
@RequestMapping("signIn")
@Slf4j
public class SignInController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String getSignInPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "signIn";
    }


    @PostMapping
    public String loginUser(@ModelAttribute LoginRequest loginRequest, Model model, HttpSession session) {
        log.info("Login request: {}", loginRequest);

        Response response = null;
        try {
            response = restTemplate.postForObject("/api/auth/login", loginRequest, Response.class);
        }catch (Exception e) {
            log.error("Error in loginUser: {}", e.getMessage());
            model.addAttribute("message", "Please try again!");
        }

        if(Optional.ofNullable(response)
                .map(Response::getData)
                .filter(d -> d.equals(SUCCESS))
                .isPresent()){
            model.addAttribute("message", "success");
            session.setAttribute("currentUser", loginRequest.getEmail());
        } else {
            log.info("{}",Optional.ofNullable(response).map(Response::getData).get());
            model.addAttribute("message", Optional.ofNullable(response).map(Response::getData).get());
        }

        return "signIn";
    }
}
