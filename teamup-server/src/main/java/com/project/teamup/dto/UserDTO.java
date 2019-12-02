package com.project.teamup.dto;


import com.project.teamup.model.UserLanguage;
import com.project.teamup.model.UserRole;
import com.project.teamup.model.UserSeniority;
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
public class UserDTO {
    //TODO: include supervisor
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private byte[] picture;
    private UserLanguage language;
    private UserRole role;
    private UserSeniority seniority;
    private LocationDTO location;
    private CompanyDTO company;
    private List<SkillDTO> skills;
    private List<ProjectExperienceDTO> projectExperiences;
}
