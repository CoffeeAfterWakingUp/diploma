package com.example.diplomaprojectgeneticcode.rest;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.Role;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.UserRole;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/rest/teacher")
public class TeacherEditRestController {
    private final UserService newUserService;

    public TeacherEditRestController(UserService newUserService){
        this.newUserService = newUserService;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = "multipart/form-data")
    public User editTeacher(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("email") String email,
    @RequestParam("userName") String userName,
    @RequestParam("password") String password) throws Exception {
        User teacher = new User();
        teacher.setName(firstName);
        teacher.setSurname(lastName);
        teacher.setEmail(email);
//        Role role = new Role();
//        role.setRoleName(UserRole.TEACHER);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        teacher.setRoles(roles);
        teacher.setPassword(password);
        return newUserService.create(teacher);
    }
}
