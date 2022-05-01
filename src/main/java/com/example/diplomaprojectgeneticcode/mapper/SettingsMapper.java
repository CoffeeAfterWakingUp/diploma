package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.SettingsDTO;
import com.example.diplomaprojectgeneticcode.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SettingsMapper {

    public SettingsDTO toDto(User user) {

        SettingsDTO dto = new SettingsDTO();
        Optional<User> userOpt = Optional.of(user);

        userOpt.map(User::getName).ifPresent(dto::setName);
        userOpt.map(User::getSurname).ifPresent(dto::setSurname);
        userOpt.map(User::getEmail).ifPresent(dto::setEmail);
        userOpt.map(User::getSocialMediaUrl).ifPresent(dto::setSocialMediaUrl);
        userOpt.map(User::getPersonalWebsiteUrl).ifPresent(dto::setPersonalWebUrl);
        userOpt.map(User::getImage).ifPresent(dto::setImage);
        userOpt.map(User::getBiography).ifPresent(dto::setBiography);
        userOpt.map(User::getPhoneNumber).ifPresent(dto::setPhoneNumber);

        return dto;

    }

}
