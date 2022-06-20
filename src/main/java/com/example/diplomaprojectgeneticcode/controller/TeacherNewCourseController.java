package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.controller.util.SessionManager;
import com.example.diplomaprojectgeneticcode.dto.CourseBasicsDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.mapper.CourseMapper;
import com.example.diplomaprojectgeneticcode.repo.CourseRepo;
import com.example.diplomaprojectgeneticcode.service.client.CategoryService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.FileService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.getUserSession;
import static com.example.diplomaprojectgeneticcode.util.Constant.TEACHER_NEW_COURSE_PAGE;

@Controller
@RequestMapping("/t/new-course")
@RequiredArgsConstructor
public class TeacherNewCourseController {

    private final CourseRepo courseRepo;
    private final CourseService courseService;
    private final CategoryService categoryClientService;
    private final FileService fileService;
    private final CourseMapper courseMapper;
    private final UserService userService;
    private final com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService categoryService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("languages", courseService.getLanguages());
        model.addAttribute("levels", courseService.getLevels());
        model.addAttribute("categories", categoryClientService.getCategories());
        model.addAttribute("currencies", categoryClientService.getCurrencies());
    }


    @GetMapping("/basics")
    public ModelAndView getNewCoursePage(HttpSession session) {
        String username = getUserSession(session);

//        if(username == null) {
//            return new ModelAndView("redirect:/signIn");
//        }
        ModelAndView modelAndView = new ModelAndView(TEACHER_NEW_COURSE_PAGE);
        CourseBasicsDTO courseBasicsDTO = new CourseBasicsDTO();
        modelAndView.addObject("courseBasics", courseBasicsDTO);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveNewCourse(@ModelAttribute CourseBasicsDTO dto,
                                      @RequestParam("image") MultipartFile image,
                                      @RequestParam("video") MultipartFile video,
                                      HttpSession session) {
        String imageUrl = fileService.getFileName(image);
        String videoUrl = fileService.getFileName(video);
        if(imageUrl.isBlank()) {
            imageUrl = fileService.getPlaceholderImage();
        }
        if(videoUrl.isBlank()) {
            videoUrl = fileService.getPlaceholderImage();
        }
        dto.setImageUrl(imageUrl);
        dto.setVideoUrl(videoUrl);

        String username = getUserSession(session);
        User teacher = userService.getUserByEmail("180103273@stu.sdu.edu.kz");
        Course course = new Course();
        course.setTitle(dto.getCourseTitle());
        course.setSubtitle(dto.getCourseSubtitle());
        course.setDescription(dto.getCourseDescription());
        course.setImage(dto.getImageUrl());
        course.setPromoVideoUrl(dto.getVideoUrl());
        course.setCurrency(dto.getCurrency());
        course.setCourseLang(dto.getCourseLang());
        course.setCourseLevel(CourseLevel.valueOf(dto.getCourseLevel()));
        course.setPrice(dto.getPrice());
        course.setCategory(categoryService.getCategoryByName(dto.getCourseCategory()));
        course.setTeachers(Set.of(teacher));
        Course createdCourse = courseRepo.save(course);

        UUID createdCourseId = createdCourse.getId();
        return new ModelAndView("redirect:/t/new-course/" + createdCourseId + "/curriculum");
    }
}
