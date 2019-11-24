package com.project.teamup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExperienceDTO {
    private ProjectDTO project;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
