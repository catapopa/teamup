package com.project.teamup.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_user_skills")
public class ProjectUserSkill {
    @EmbeddedId
    private ProjectUserSkillId id;
    @MapsId("projectId")
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;
    @MapsId("userId")
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    @MapsId("skillId")
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Skill skill;
}
