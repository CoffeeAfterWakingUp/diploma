package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.entity.Section;
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
public class SectionMapper {

    private final ContentMapper contentMapper;

    public SectionDTO toDto(Section section) {
        log.info("Section: {}", section);
        Optional<Section> sectionOpt = Optional.ofNullable(section);
        SectionDTO sectionDTO = new SectionDTO();

        sectionOpt.map(Section::getId).ifPresent(sectionDTO::setId);
        sectionOpt.map(Section::getName).ifPresent(sectionDTO::setName);
        sectionOpt.map(Section::getOrderNumber).ifPresent(sectionDTO::setOrderNumber);
        sectionOpt.map(Section::getCreatedAt).ifPresent(sectionDTO::setCreatedAt);
        sectionOpt.map(Section::getUpdatedAt).ifPresent(sectionDTO::setUpdatedAt);
        sectionOpt.map(Section::getContents)
                .ifPresent(contents -> sectionDTO.setContents(contentMapper.toDto(contents)));



        log.info("SectionDTO: {}", sectionDTO);
        return sectionDTO;
    }

    public List<SectionDTO> toDto(List<Section> sections) {
        List<SectionDTO> sectionsDto = Optional.ofNullable(sections)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        log.info("SectionsDto: {}", sectionsDto);
        return sectionsDto;
    }
}
