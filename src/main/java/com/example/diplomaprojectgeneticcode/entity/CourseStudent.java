package com.example.diplomaprojectgeneticcode.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_student")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CourseStudent implements Serializable {

    @EmbeddedId
    private CourseStudentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "rolled_in")
    private LocalDateTime rolledIn;

    public CourseStudent(Course course, User student) {
        this.course = course;
        this.student = student;
        this.id = new CourseStudentId(student.getId(), course.getId());
        this.rolledIn = LocalDateTime.now();
    }


}
