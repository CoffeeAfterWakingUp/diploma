package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.Section;
import com.example.diplomaprojectgeneticcode.repo.SectionRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepo sectionRepo;

    @Override
    public List<Section> getSectionsByCourseId(UUID courseId) {
        return sectionRepo.getSectionsByCourseId(courseId);
    }

    @Override
    public Section getSectionById(UUID id) {
        return sectionRepo.findById(id)
                .orElse(new Section());
    }
}
