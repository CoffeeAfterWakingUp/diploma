package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.UserDTO;
import com.example.diplomaprojectgeneticcode.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMapper {


    public UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        Optional<User> userOpt = Optional.ofNullable(user);

        userOpt.map(User::getId).ifPresent(userDto::setId);
        userOpt.map(User::getEmail).ifPresent(userDto::setEmail);
        userOpt.map(User::getName).ifPresent(userDto::setName);
        userOpt.map(User::getSurname).ifPresent(userDto::setSurname);
        userOpt.map(User::getStatus).ifPresent(status -> userDto.setStatus(status.toString()));
        userOpt.map(User::getBiography).ifPresent(userDto::setBiography);
        userOpt.map(User::getImage).ifPresent(userDto::setImage);
        userOpt.map(User::getEnabled).ifPresent(userDto::setEnabled);
        userOpt.map(User::getLocked).ifPresent(userDto::setLocked);

        return userDto;
    }
}
