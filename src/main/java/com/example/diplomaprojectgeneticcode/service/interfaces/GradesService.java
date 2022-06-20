package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.Grade;
import com.example.diplomaprojectgeneticcode.entity.datatables.Page;
import com.example.diplomaprojectgeneticcode.rest.PagingRequest;
import com.example.diplomaprojectgeneticcode.dto.GradesDTO;

import java.util.List;

public interface GradesService {
    List<Grade> getGrades();
    Page<GradesDTO> gradesDtoList(PagingRequest pagingRequest);
}
