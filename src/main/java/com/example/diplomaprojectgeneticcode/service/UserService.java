package com.example.diplomaprojectgeneticcode.service;

import com.example.diplomaprojectgeneticcode.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();
    User getById(UUID id);
    User create(User user);
    User update(User user, UUID id);
    boolean delete(UUID id);

}