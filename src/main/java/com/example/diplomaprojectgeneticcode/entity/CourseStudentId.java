package com.example.diplomaprojectgeneticcode.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CourseStudentId implements Serializable {

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "course_id")
    private UUID courseId;

}
