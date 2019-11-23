package com.project.teamup.dto;


import com.project.teamup.model.UserSkillLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSkillDTO {
    private Long id;
    private TechnologyDTO technology;
    private UserSkillLevel level;
}
