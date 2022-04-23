package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.ContentDTO;
import com.example.diplomaprojectgeneticcode.entity.Content;
import com.example.diplomaprojectgeneticcode.enums.ContentType;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import com.example.diplomaprojectgeneticcode.service.interfaces.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContentMapper {

    private final SectionService sectionService;
    private final CourseService courseService;



    public ContentDTO toDto(Content content) {
        log.info("Content: {}", content);
        Optional<Content> contentOpt = Optional.ofNullable(content);
        ContentDTO contentDTO = new ContentDTO();

        contentOpt.map(Content::getId).ifPresent(contentDTO::setId);
        contentOpt.map(Content::getName).ifPresent(contentDTO::setName);
        contentOpt.map(Content::getType)
                .ifPresent(type -> contentDTO.setType(type.toString()));
        contentOpt.map(Content::getSection)
                .ifPresent(section -> {
                    contentDTO.setSectionName(section.getName());
                    contentDTO.setSectionId(section.getId());
                });
        contentOpt.map(Content::getCourse)
                .ifPresent(course -> {
                    contentDTO.setCourseId(course.getId());
                    contentDTO.setCourseName(course.getTitle());
                });
        contentOpt.map(Content::getContentUrl).ifPresent(contentDTO::setContentUrl);
        contentOpt.map(Content::getEnabled).ifPresent(contentDTO::setEnabled);
        contentOpt.map(Content::getUpdatedAt).ifPresent(contentDTO::setUpdatedAt);
        contentOpt.map(Content::getCreatedAt).ifPresent(contentDTO::setCreatedAt);


        log.info("ContentDTO: {}", contentDTO);
        return contentDTO;
    }

    public List<ContentDTO> toDto(List<Content> contents) {
        return Optional.ofNullable(contents)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Content toEntity(ContentDTO contentDTO) {

        Content content = new Content();
        Optional<ContentDTO> contentDTOOpt = Optional.ofNullable(contentDTO);

        contentDTOOpt.map(ContentDTO::getId).ifPresent(content::setId);
        contentDTOOpt.map(ContentDTO::getName).ifPresent(content::setName);
        contentDTOOpt.map(ContentDTO::getType).ifPresent(type -> content.setType(ContentType.valueOf(type)));
        contentDTOOpt.map(ContentDTO::getSectionId).ifPresent(sectionId -> content.setSection(sectionService.getSectionById(sectionId)));
        contentDTOOpt.map(ContentDTO::getCourseId).ifPresent(courseId -> content.setCourse(courseService.getCourseById(courseId)));
        contentDTOOpt.map(ContentDTO::getCreatedAt).ifPresent(content::setCreatedAt);
        contentDTOOpt.map(ContentDTO::getUpdatedAt).ifPresent(content::setUpdatedAt);
        contentDTOOpt.map(ContentDTO::getContentUrl).ifPresent(content::setContentUrl);
        contentDTOOpt.map(ContentDTO::isEnabled).ifPresent(content::setEnabled);

        return content;
    }
}
