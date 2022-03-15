package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.http.Response;
import com.example.diplomaprojectgeneticcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.getAll())
                        .build()
        );
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.getById(id))
                        .build()
        );
    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> createUser(@RequestBody User user) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .message(SUCCESS)
                        .data(userService.create(user))
                        .build()
        );
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> updateUser(@PathVariable UUID id, @RequestBody User user) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.update(user, id))
                        .build()
        );
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userService.delete(id))
                        .build()
        );
    }






}
