package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.dto.PasswordDTO;
import com.example.diplomaprojectgeneticcode.dto.SettingsDTO;
import com.example.diplomaprojectgeneticcode.entity.StudentContent;
import com.example.diplomaprojectgeneticcode.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();
    User getById(UUID id);
    User create(User user);
    User update(User user, UUID id);
    boolean delete(UUID id);
    void enableUser(UUID id);
    User getUserByEmail(String email);

    List<StudentContent> getGradesOfStudent(UUID studentId, UUID courseId);

    List<StudentContent> getAttendanceOfStudent(UUID studentId, UUID courseId);

    String updateSettings(UUID id, SettingsDTO settingsDTO);

    String changePassword(UUID id, PasswordDTO passwordDTO);
}
