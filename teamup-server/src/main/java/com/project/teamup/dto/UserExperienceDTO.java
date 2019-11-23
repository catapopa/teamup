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
public class UserExperienceDTO {
    private String userId;
    private String fullName;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
