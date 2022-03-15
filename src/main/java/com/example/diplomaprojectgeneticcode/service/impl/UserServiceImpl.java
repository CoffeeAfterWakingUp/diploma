package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.repo.UserRepo;
import com.example.diplomaprojectgeneticcode.service.UserService;
import com.example.diplomaprojectgeneticcode.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.USER_NOT_FOUND_MSG;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

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
        if(StringUtils.isBlank(String.valueOf(id))) {
            throw new NoSuchElementException();
        }
        Optional<User> userOpt = userRepo.findById(id);
        if(userOpt.isEmpty()) {
            throw new RuntimeException("User Is Null");
        }
        log.info("Got the user: {}", userOpt.get());
        return userOpt.get();
    }

    @Override
    public User create(User user) {
        log.info("Got user for create: {}", user);
        if(user == null) {
            throw new RuntimeException("User is Null");
        }
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
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
}
