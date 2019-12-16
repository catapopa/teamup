package com.project.teamup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExperienceDTO {
    private ProjectDTO project;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<SkillDTO> skills;
}
