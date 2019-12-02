package com.project.teamup.model;

import lombok.*;

import javax.persistence.*;

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
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private UserLanguage language;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "technology")
    private Technology technology;
    @Column
    @Enumerated(EnumType.STRING)
    private UserSkillLevel level;
}
