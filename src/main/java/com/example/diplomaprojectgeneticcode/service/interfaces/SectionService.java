package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.Section;

import java.util.List;
import java.util.UUID;

public interface SectionService {

    List<Section> getSectionsByCourseId(UUID courseId);
    Section getSectionById(UUID id);
}
