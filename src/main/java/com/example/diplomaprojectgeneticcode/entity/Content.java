package com.example.diplomaprojectgeneticcode.entity;

import com.example.diplomaprojectgeneticcode.enums.ContentType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "content")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String contentUrl;

    private Integer orderNumber;



    private Boolean enabled = true;

    @OneToMany(
            mappedBy = "content",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StudentContent> students = new ArrayList<>();




    public Content(String name, ContentType type, Section section, Course course, String contentUrl) {
        this.name = name;
        this.type = type;
        this.section = section;
        this.course = course;
        this.createdAt = LocalDateTime.now();
        this.contentUrl = contentUrl;
    }
}