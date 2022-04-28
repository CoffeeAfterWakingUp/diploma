package com.example.diplomaprojectgeneticcode.dto;

import com.example.diplomaprojectgeneticcode.entity.Grade;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data @NoArgsConstructor
public class GradesDTO {
    private Integer id;
    private String user_id;
    private String course_id;
    private String quiz1;
    private String quiz2;
    private String quiz3;
    private String exam;
    private Date date;
    private String total;
    private String mentor;

    public static GradesDTO convertToDto(Grade grade){

        GradesDTO dto = new GradesDTO();
        dto.id = grade.getId();
        dto.user_id = grade.getUser_id();
        dto.course_id = grade.getCourse_id();
        dto.quiz1 = grade.getQuiz1();
        dto.quiz2 = grade.getQuiz2();
        dto.quiz3 = grade.getQuiz3();
        dto.exam = grade.getExam();
        dto.date = grade.getDate();
        dto.total = grade.getTotal();
        dto.mentor = grade.getMentor();
        return dto;
    }

    public static List<GradesDTO> convertToDto(List<Grade> grades){
        List<GradesDTO> dtoList = new ArrayList<>();
        for (Grade grade: grades){
            dtoList.add(convertToDto(grade));
        }
        return dtoList;
    }

}
