package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.controller.util.PriceManager;
import com.example.diplomaprojectgeneticcode.dto.CourseBasicsDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.CategoryService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.TEACHER_COURSE_BASICS_PAGE;

@Controller
@RequestMapping("/t/my-courses")
@RequiredArgsConstructor
public class TeacherCourseBasics {

    private final CourseService courseService;
    private final CategoryService categoryService;
    private final FileService fileService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("languages", courseService.getLanguages());
        model.addAttribute("levels", courseService.getLevels());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("currencies", categoryService.getCurrencies());
    }

    @GetMapping("/{courseId}/manage/basics")
    public ModelAndView getTeacherCourseBasicsPage(@PathVariable UUID courseId) {
        ModelAndView modelAndView = new ModelAndView(TEACHER_COURSE_BASICS_PAGE);
        CourseDTO course = courseService.getCourseById(courseId);
        CourseBasicsDTO courseBasicsDTO = CourseBasicsDTO.builder()
                .courseTitle(course.getTitle())
                .courseSubtitle(course.getSubtitle())
                .courseDescription(course.getDescription())
                .courseLevel(course.getCourseLevel())
                .courseLang(course.getCourseLang())
                .courseCategory(course.getCategory())
                .imageUrl(course.getImage())
                .videoUrl(course.getPromoVideo())
                .currency(course.getCurrency())
                .price(PriceManager.getRoundedPrice(course.getPrice()))
                .build();
        modelAndView.addObject("course", course);
        modelAndView.addObject("courseBasics", courseBasicsDTO);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveBasics(@ModelAttribute CourseBasicsDTO courseBasics,
                                   @RequestParam("image") MultipartFile image,
                                   @RequestParam("video") MultipartFile video,
                                   @RequestParam("id") UUID id,
                                   @RequestParam("imageValue") String imageValue,
                                   @RequestParam("videoValue") String videoValue) {

        ModelAndView modelAndView = new ModelAndView("redirect:/t/my-courses/" + String.format("%s/manage/basics", id));
        String imageUrl = fileService.getFileName(image);
        String videoUrl = fileService.getFileName(video);

        // imageValue пустой, imageUrl пустой, то плэйсхолдер
        // imageValue бурынгысы, imageUrl новый, то imageUrl
        // imageValue плэйсхолдер, imageUrl пустой, to imageValue
        // imageValue бурынгысы, imageUrl пустой, то imageValue

        if(imageValue.isBlank() && imageUrl.isBlank()) {
            courseBasics.setImageUrl(fileService.getPlaceholderImage());
        } else if(!imageValue.isBlank() && !imageUrl.isBlank() && !imageValue.equals(fileService.getPlaceholderImage())) {
            courseBasics.setImageUrl(imageUrl);
        } else if(imageValue.equals(fileService.getPlaceholderImage()) && imageUrl.isBlank()) {
            courseBasics.setImageUrl(imageValue);
        } else if(!imageValue.isBlank() && imageUrl.isBlank()) {
            courseBasics.setImageUrl(imageValue);
        } else if(imageValue.isBlank() && !imageUrl.isBlank()) {
            courseBasics.setImageUrl(imageUrl);
        }


        if(videoValue.isBlank() && videoUrl.isBlank()) {
            courseBasics.setVideoUrl(fileService.getPlaceholderImage());
        } else if(!videoValue.isBlank() && !videoUrl.isBlank() && !videoValue.equals(fileService.getPlaceholderImage())) {
            courseBasics.setVideoUrl(videoUrl);
        } else if(videoValue.equals(fileService.getPlaceholderImage()) && videoUrl.isBlank()) {
            courseBasics.setVideoUrl(videoValue);
        } else if(!videoValue.isBlank() && videoUrl.isBlank()) {
            courseBasics.setVideoUrl(videoValue);
        } else if(videoValue.isBlank() && !videoUrl.isBlank()) {
            courseBasics.setVideoUrl(videoUrl);
        }


        boolean response = courseService.updateCourseBasics(id, courseBasics);

        modelAndView.addObject("response", response);

        return modelAndView;
    }

}
