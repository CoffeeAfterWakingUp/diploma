package com.example.diplomaprojectgeneticcode.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "section")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"contents"})
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Content> contents = new ArrayList<>();

    public Section(String name, Long orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.createdAt = LocalDateTime.now();
    }
}
