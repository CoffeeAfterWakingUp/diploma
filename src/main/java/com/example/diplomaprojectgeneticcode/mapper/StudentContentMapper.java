package com.example.diplomaprojectgeneticcode.mapper;


import com.example.diplomaprojectgeneticcode.dto.StudentContentDTO;
import com.example.diplomaprojectgeneticcode.entity.StudentContent;
import com.example.diplomaprojectgeneticcode.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentContentMapper {

    private final ContentMapper contentMapper;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;


    public StudentContentDTO toDto(StudentContent studentContent) {

        StudentContentDTO dto = new StudentContentDTO();
        Optional<StudentContent> studentContentOpt = Optional.of(studentContent);

        studentContentOpt.map(StudentContent::getStudent).ifPresent(student -> dto.setStudent(userMapper.toDto(student)));
        studentContentOpt.map(StudentContent::getContent).ifPresent(content -> {
            dto.setContent(contentMapper.toDto(content));
            dto.setCourse(courseMapper.toDto(content.getCourse()));
        });
        studentContentOpt.map(StudentContent::getTeacherName).ifPresent(dto::setTeacher);
        studentContentOpt.map(StudentContent::getCompleted).ifPresent(dto::setCompleted);
        studentContentOpt.map(StudentContent::getAttended).ifPresent(dto::setAttended);
        studentContentOpt.map(StudentContent::getCalculatedWeight).ifPresent(dto::setCalculatedWeight);
        studentContentOpt.map(StudentContent::getFeedback).ifPresent(dto::setFeedback);
        studentContentOpt.map(StudentContent::getGrade).ifPresent(dto::setGrade);
        studentContentOpt.map(StudentContent::getMaxRange).ifPresent(dto::setMaxRange);
        studentContentOpt.map(StudentContent::getMinRange).ifPresent(dto::setMinRange);
        studentContentOpt.map(StudentContent::getPercentage).ifPresent(dto::setPercentage);


        return dto;
    }

    public List<StudentContentDTO> toDto(List<StudentContent> studentContents) {
        return Optional.ofNullable(studentContents)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
