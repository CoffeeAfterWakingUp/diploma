package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.StudentContentDTO;
import com.example.diplomaprojectgeneticcode.dto.UserDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.STUDENT_ATTENDANCE_PAGE;
import static com.example.diplomaprojectgeneticcode.util.Constant.USER_SESSION;

@Controller
@RequestMapping("my-courses")
@RequiredArgsConstructor
public class StudentAttendanceController {
    private final CourseService courseService;
    private final UserService userService;

    @ModelAttribute
    public void setAttributes(Model model) {
        model.addAttribute("page", "Attendance");
    }

    @GetMapping("/{courseId}/attendance")
    public ModelAndView getAttendancePage(@PathVariable UUID courseId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(STUDENT_ATTENDANCE_PAGE);

        String username = (String) session.getAttribute(USER_SESSION);
        // #TODO change username
        UserDTO user = userService.getUserByUsername("180103273@stu.sdu.edu.kz");

        CourseDTO course = courseService.getCourseById(courseId);

        // get the attendance of the user by course and content
        List<StudentContentDTO> attendance = userService.getAttendanceOfStudent(user.getId(), courseId);

        long attendedCount = attendance.stream()
                .map(StudentContentDTO::getAttended)
                .filter(attended -> attended)
                .count();

        int attendanceSize = attendance.isEmpty() ? 1 : attendance.size();
        int attendedPercent = Math.toIntExact((attendedCount * 100) / attendanceSize);
        int absencePercent = 100 - attendedPercent;

        modelAndView.addObject("course", course);
        modelAndView.addObject("attendance", attendance);
        modelAndView.addObject("attendedPercent", attendedPercent);
        modelAndView.addObject("absencePercent", absencePercent);

        return modelAndView;
    }
}
