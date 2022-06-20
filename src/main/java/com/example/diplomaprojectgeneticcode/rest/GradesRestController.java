package com.example.diplomaprojectgeneticcode.rest;
import com.example.diplomaprojectgeneticcode.dto.GradesDTO;
import com.example.diplomaprojectgeneticcode.entity.datatables.Page;
import com.example.diplomaprojectgeneticcode.service.interfaces.GradesService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/rest/grades")
public class GradesRestController {
    private final GradesService gradesService;

    public GradesRestController(GradesService gradesService){
        this.gradesService = gradesService;
    }
    @PostMapping("/list")
    public Page<GradesDTO> list(@RequestBody PagingRequest pagingRequest) throws ParseException {
        return gradesService.gradesDtoList(pagingRequest);
    }
}
