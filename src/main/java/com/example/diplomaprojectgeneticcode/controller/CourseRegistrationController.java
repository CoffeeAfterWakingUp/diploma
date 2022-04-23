package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.CategoryService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.example.diplomaprojectgeneticcode.util.Constant.COURSE_REG_PAGE;

@Controller
@RequestMapping("/courses")
@Slf4j
@RequiredArgsConstructor
public class CourseRegistrationController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("languages", courseService.getLanguages());
        model.addAttribute("levels", courseService.getLevels());
        model.addAttribute("prices", courseService.getPriceFilters());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("page", "courses");
    }


    @GetMapping
    public String getCourseRegistrationPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            Model model) {

        Page<CourseDTO> coursesPage = courseService.findPaginated(
                courseService.getTopCourses(), PageRequest.of(page - 1, size));

        int totalPages = coursesPage.getTotalPages();

        model.addAttribute("coursesPage", coursesPage);
        model.addAttribute("pageNumbers", courseService.getPageNumbers(totalPages));
        model.addAttribute("paginationUrl", "/courses");
        //get courses title, subtitle, price, teachers' name, average rating, reviews' count, image of course

        // return course registration page
        return COURSE_REG_PAGE;
    }


    @GetMapping("/search")
    public String searchCourseByTitle(@RequestParam String title,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Model model) {

        List<CourseDTO> coursesByTitle = courseService.filterCoursesByTitle(title);
        Page<CourseDTO> coursesPage = courseService.findPaginated(coursesByTitle, PageRequest.of(page - 1, size));

        int totalPages = coursesPage.getTotalPages();

        model.addAttribute("coursesPage", coursesPage);
        model.addAttribute("pageNumbers", courseService.getPageNumbers(totalPages));
        model.addAttribute("paginationUrl", "/courses/search");

        return COURSE_REG_PAGE;
    }

    @GetMapping("/filter")
    public String filterCourses(@RequestParam(defaultValue = "") String teacher,
                                @RequestParam(defaultValue = "") String language,
                                @RequestParam(defaultValue = "") String level,
                                @RequestParam(defaultValue = "") String[] sort,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size,
                                @RequestParam(defaultValue = "") String category,
                                Model model) {

        // courses sort
        List<CourseDTO> sortedCourses = courseService.getSortedCourses(sort);

        // courses filter
        List<CourseDTO> courses = courseService.filterByTeacherAndLanguageAndLevelAndCategoryName(
                sortedCourses, teacher, language, level, category);

        // courses paginated
        Page<CourseDTO> coursesPage = courseService.findPaginated(courses, PageRequest.of(page - 1, size));

        int totalPages = coursesPage.getTotalPages();

        model.addAttribute("coursesPage", coursesPage);
        model.addAttribute("pageNumbers", courseService.getPageNumbers(totalPages));
        model.addAttribute("paginationUrl", "/courses/filter");

        return COURSE_REG_PAGE;
    }


}
