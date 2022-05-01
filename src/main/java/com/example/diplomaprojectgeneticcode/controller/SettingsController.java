package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.controller.util.SessionManager;
import com.example.diplomaprojectgeneticcode.dto.PasswordDTO;
import com.example.diplomaprojectgeneticcode.dto.SettingsDTO;
import com.example.diplomaprojectgeneticcode.dto.UserDTO;
import com.example.diplomaprojectgeneticcode.service.client.FileService;
import com.example.diplomaprojectgeneticcode.service.client.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.example.diplomaprojectgeneticcode.util.Constant.SETTINGS_PAGE;

@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final FileService fileService;
    private final UserService userService;

    @ModelAttribute
    public void setAttributes(Model model) {
        model.addAttribute("password", new PasswordDTO());
    }


    @GetMapping
    public ModelAndView getSettingsPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(SETTINGS_PAGE);
        String username = SessionManager.getUserSession(session);
        UserDTO user = userService.getUserByUsername("180103273@stu.sdu.edu.kz");
        SettingsDTO settings = userService.getSettings(user.getId());

        modelAndView.addObject("settings", settings);


        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveSettingsChanges(@ModelAttribute SettingsDTO settingsDTO,
                                            @RequestParam("file") MultipartFile file,
                                            HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("redirect:/settings");
        String username = SessionManager.getUserSession(session);
        UserDTO user = userService.getUserByUsername("180103273@stu.sdu.edu.kz");

        settingsDTO.setImage(fileService.getFileName(file));
        String response = userService.saveSettings(user.getId(), settingsDTO);

        modelAndView.addObject("response", response);

        return modelAndView;
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@ModelAttribute PasswordDTO password,
                                       HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("redirect:/settings");
        String username = SessionManager.getUserSession(session);
        UserDTO user = userService.getUserByUsername("180103273@stu.sdu.edu.kz");

        String response = userService.changePassword(user.getId(), password);

        modelAndView.addObject("response", response);

        return modelAndView;
    }
}
