package com.project.teamup.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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
    @ElementCollection(targetClass = String.class)
    @MapKeyColumn(name = "language")
    @CollectionTable(name = "skill_translations",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private Map<UserLanguage, String> translations;
}
