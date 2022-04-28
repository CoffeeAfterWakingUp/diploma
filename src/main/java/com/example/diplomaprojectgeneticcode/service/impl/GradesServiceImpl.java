package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.GradesDTO;
import com.example.diplomaprojectgeneticcode.entity.Grade;
import com.example.diplomaprojectgeneticcode.entity.datatables.Page;
import com.example.diplomaprojectgeneticcode.repo.GradesRepo;
import com.example.diplomaprojectgeneticcode.rest.PagingRequest;
import com.example.diplomaprojectgeneticcode.service.interfaces.GradesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradesServiceImpl implements GradesService {
    private final GradesRepo gradesRepo;

    @Override
    public List<Grade> getGrades(){
        return gradesRepo.getGrades();
    }
    private List<Grade> listAbonomentsByFilters() {
        System.out.println("getGrades:"  + gradesRepo.getGrades());
        System.out.println("findAll:"  + gradesRepo.findAll());
        return gradesRepo.findAll();
    }
    @Override
    public Page<GradesDTO> gradesDtoList(PagingRequest pagingRequest) {
        List<Grade> allGrades = listAbonomentsByFilters();
        return getDtoPage(GradesDTO.convertToDto(allGrades), pagingRequest);
    }
    private Page<GradesDTO> getDtoPage(List<GradesDTO> gradesDTOList, PagingRequest pagingRequest) {
        List<GradesDTO> filtered = gradesDTOList.stream()
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = gradesDTOList.stream()
                .count();

        Page<GradesDTO> page = new Page<>(filtered);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }
}
