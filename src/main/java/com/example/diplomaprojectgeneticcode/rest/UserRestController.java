package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.*;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.mapper.SettingsMapper;
import com.example.diplomaprojectgeneticcode.mapper.StudentContentMapper;
import com.example.diplomaprojectgeneticcode.mapper.UserMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserRestController extends AbstractRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final StudentContentMapper studentContentMapper;
    private final SettingsMapper settingsMapper;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<User>>> getAll() {
        return successOK(userService.getAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<User>> getById(@PathVariable UUID id) {
        return successOK(userService.getById(id));
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<ResponseDTO<UserDTO>> getByUsername(@PathVariable String username) {
        return successOK(userMapper.toDto(userService.getUserByEmail(username)));
    }

    @GetMapping("/{studentId}/{courseId}/grades")
    public ResponseEntity<ResponseDTO<List<StudentContentDTO>>> getGradesOfStudent(@PathVariable UUID studentId,
                                                                                   @PathVariable UUID courseId) {
        return successOK(
                studentContentMapper.toDto(
                        userService.getGradesOfStudent(studentId, courseId)
                )
        );
    }

    @GetMapping("/{studentId}/{courseId}/attendance")
    public ResponseEntity<ResponseDTO<List<StudentContentDTO>>> getAttendanceOfStudent(@PathVariable UUID studentId,
                                                                                       @PathVariable UUID courseId) {
        return successOK(
                studentContentMapper.toDto(
                        userService.getAttendanceOfStudent(studentId, courseId)
                )
        );
    }

    @GetMapping("{id}/settings/details")
    public ResponseEntity<ResponseDTO<SettingsDTO>> getSettingsDetailsOfUser(@PathVariable UUID id) {
        return successOK(
                settingsMapper.toDto(
                        userService.getById(id)
                )
        );
    }

    @PatchMapping("/{id}/settings/details")
    public ResponseEntity<ResponseDTO<String>> saveSettings(@PathVariable UUID id,
                             @RequestBody SettingsDTO settingsDTO) {

        return successOK(userService.updateSettings(id, settingsDTO));
    }

    @PatchMapping("{id}/settings/changePassword")
    public ResponseEntity<ResponseDTO<String>> changePassword(@PathVariable UUID id,
                                                              @RequestBody PasswordDTO passwordDTO) {
        return successOK(userService.changePassword(id, passwordDTO));
    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> createUser(@RequestBody User user) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timestamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .message(SUCCESS)
                        .data(userService.create(user))
                        .build()
        );
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> updateUser(@PathVariable UUID id, @RequestBody User user) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.update(user, id))
                        .build()
        );
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.delete(id))
                        .build()
        );
    }


}
