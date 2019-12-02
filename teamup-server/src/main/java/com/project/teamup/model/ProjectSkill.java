package com.project.teamup.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_skills_projects")
public class ProjectSkill {
    @EmbeddedId
    private ProjectSkillId id;
    @MapsId("projectUserExperienceId")
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProjectUserExperience projectUserExperience;
    @MapsId("skillId")
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Skill skill;
}
