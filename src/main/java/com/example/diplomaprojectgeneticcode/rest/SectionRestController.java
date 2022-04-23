package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.mapper.SectionMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/section")
@RequiredArgsConstructor
public class SectionRestController extends AbstractRestController {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    @GetMapping("{courseId}/sections")
    public ResponseEntity<ResponseDTO<List<SectionDTO>>> getSectionsByCourseId(@PathVariable UUID courseId) {
        return successOK(
                sectionMapper.toDto(
                        sectionService.getSectionsByCourseId(courseId)
                )
        );
    }

}
