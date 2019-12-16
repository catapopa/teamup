package com.project.teamup.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skills")
public class Skill {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "technology")
    private Technology technology;
    @Column
    @Enumerated(EnumType.STRING)
    private UserSkillLevel level;
    @ElementCollection
    private List<Translation> translations;
}
