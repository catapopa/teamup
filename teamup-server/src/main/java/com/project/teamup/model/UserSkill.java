package com.project.teamup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skills")
public class UserSkill {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @JsonBackReference
    @JoinColumn(name = "\"user\"")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "technology")
    @Valid
    private Technology technology;
    @Column
    @Enumerated(EnumType.STRING)
    private UserSkillLevel level;
    //TODO: separate user skill in another many to many table
    //TODO: add i18n to the skill, technology, technology area
}
