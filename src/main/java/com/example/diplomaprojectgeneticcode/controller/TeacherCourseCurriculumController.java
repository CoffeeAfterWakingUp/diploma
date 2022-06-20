package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.dto.CourseBasicsDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.entity.Content;
import com.example.diplomaprojectgeneticcode.entity.Section;
import com.example.diplomaprojectgeneticcode.enums.ContentType;
import com.example.diplomaprojectgeneticcode.repo.ContentRepo;
import com.example.diplomaprojectgeneticcode.repo.SectionRepo;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.TEACHER_COURSE_CURRICULUM_PAGE;

@Controller
@RequestMapping("/t/new-course")
@RequiredArgsConstructor
public class TeacherCourseCurriculumController {
    private final CourseService courseService;
    private final SectionService sectionService;
    private final SectionRepo sectionRepo;
    private final ContentRepo contentRepo;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("contentTypes", courseService.getContentTypes());
    }


    @GetMapping("{id}/curriculum")
    public ModelAndView getPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView(TEACHER_COURSE_CURRICULUM_PAGE);
        CourseDTO course = courseService.getCourseById(id);


        List<SectionDTO> sections = Optional.ofNullable(sectionService.getSectionsByCourseId(id)).orElse(new ArrayList<>());
        Long lastOrderNumber = 0L;
        if(!sections.isEmpty()) {
            lastOrderNumber = sections.get(sections.size() - 1).getOrderNumber();
        }

        modelAndView.addObject("course", course);
        modelAndView.addObject("courseBasics", new CourseBasicsDTO());
        modelAndView.addObject("sections", sections);
        modelAndView.addObject("nextOrderNumber", (lastOrderNumber + 1));
        return modelAndView;
    }

    @PostMapping("/curriculum/save")
    public ModelAndView save(@RequestParam("newSection") String newSection,
                             @RequestParam("sectionId") UUID sectionId,
                             @RequestParam("contentType") String contentType,
                             @RequestParam("desc") String desc,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("id") UUID id,
                             @RequestParam("orderNumber") Long orderNumber,
                             @RequestParam("contentName") String contentName) {

        Section section;

        if (sectionId.equals("1")) {
            section = sectionRepo.save(new Section(newSection, orderNumber));
        } else {
            section = sectionRepo.findById(sectionId).get();
        }

        contentType = contentType.toUpperCase();
        if (contentType.equalsIgnoreCase("Lecture")) {
            contentRepo.save(new Content());
        } else if(contentType.equalsIgnoreCase("Video")) {

        } else if(contentType.equalsIgnoreCase("Download")) {

        }

        return null;

    }
}
