package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.PasswordDTO;
import com.example.diplomaprojectgeneticcode.dto.SettingsDTO;
import com.example.diplomaprojectgeneticcode.entity.StudentContent;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.repo.StudentContentRepo;
import com.example.diplomaprojectgeneticcode.repo.UserRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final StudentContentRepo studentContentRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAll() {
        log.info("Getting all users");
        List<User> users = userRepo.findAll();
        log.info("Got users: {}", users);
        return users;
    }

    @Override
    public User getById(UUID id) {
        log.info("Getting the user by id: {}", id);
        if (StringUtils.isBlank(String.valueOf(id))) {
            throw new NoSuchElementException();
        }
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User Is Null");
        }
        log.info("Got the user: {}", userOpt.get());
        return userOpt.get();
    }

    @Override
    public User create(User user) {
        log.info("Got user for create: {}", user);
        if (user == null) {
            throw new RuntimeException("User is Null");
        }
        User newUser = userRepo.save(user);
        log.info("Created user: {}", newUser);
        return newUser;
    }

    @Override
    public User update(User user, UUID id) {
        log.info("Got user and id for update: {}, {}", user, id);
        User updatedUser = userRepo.save(user);
        log.info("Updated user: {}", updatedUser);
        return updatedUser;
    }

    @Override
    public boolean delete(UUID id) {
        log.info("Got id for delete: {}", id);
        userRepo.deleteById(id);
        log.info("User is deleted");
        return true;
    }

    @Override
    public void enableUser(UUID id) {
        userRepo.enableUser(id);
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow();
    }

    @Override
    public List<StudentContent> getGradesOfStudent(UUID studentId, UUID courseId) {
        return studentContentRepo.getStudentContentsByStudentAndContent(studentId, courseId);
    }

    @Override
    public List<StudentContent> getAttendanceOfStudent(UUID studentId, UUID courseId) {
        return studentContentRepo.getStudentContentsByStudentAndContent(studentId, courseId);
    }

    @Override
    public String updateSettings(UUID id, SettingsDTO settingsDTO) {
        User user = getById(id);
        Optional<SettingsDTO> settingsOpt = Optional.ofNullable(settingsDTO);
        String s = settingsOpt.map(SettingsDTO::getName).filter(x -> !x.isBlank()).orElse(user.getName());
        log.info("{}", s);
        userRepo.updateSettings(
                id,
                settingsOpt.map(SettingsDTO::getName).filter(x -> !x.isBlank()).orElse(user.getName()),
                settingsOpt.map(SettingsDTO::getSurname).filter(x -> !x.isBlank()).orElse(user.getSurname()),
                settingsOpt.map(SettingsDTO::getEmail).filter(x -> !x.isBlank()).orElse(user.getEmail()),
                settingsOpt.map(SettingsDTO::getBiography).filter(x -> !x.isBlank()).orElse(user.getBiography()),
                settingsOpt.map(SettingsDTO::getPhoneNumber).filter(x -> !x.isBlank()).orElse(user.getPhoneNumber()),
                settingsOpt.map(SettingsDTO::getPersonalWebUrl).filter(x -> !x.isBlank()).orElse(user.getPersonalWebsiteUrl()),
                settingsOpt.map(SettingsDTO::getSocialMediaUrl).filter(x -> !x.isBlank()).orElse(user.getSocialMediaUrl()),
                settingsOpt.map(SettingsDTO::getImage).filter(x -> !x.isBlank()).orElse(user.getImage())
        );

        return "success";
    }

    @Override
    public String changePassword(UUID id, PasswordDTO passwordDTO) {
        User user = getById(id);
        if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            if(!passwordDTO.getNewPassword().isBlank()) {
                userRepo.updatePassword(id, passwordEncoder.encode(passwordDTO.getNewPassword()));
                return "success";
            }
        }
        return "failed";
    }
}
