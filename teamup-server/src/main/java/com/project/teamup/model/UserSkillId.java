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
public class UserSkillId {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "skill_id")
    private Long skillId;
}
