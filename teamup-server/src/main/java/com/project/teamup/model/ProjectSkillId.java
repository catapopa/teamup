package com.project.teamup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class ProjectSkillId {
    @Column(name = "project_user_experience_id")
    private Long projectUserExperienceId;
    @Column(name = "skill_id")
    private Long skillId;
}
