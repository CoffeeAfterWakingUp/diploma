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
public class StudentContentId implements Serializable {

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "content_id")
    private UUID contentId;
}
