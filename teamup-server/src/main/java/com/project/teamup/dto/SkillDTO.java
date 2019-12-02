package com.project.teamup.dto;


import com.project.teamup.model.UserLanguage;
import com.project.teamup.model.UserSkillLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Long id;
    private String name;
    private UserLanguage language;
    private TechnologyDTO technology;
    private UserSkillLevel level;
}
