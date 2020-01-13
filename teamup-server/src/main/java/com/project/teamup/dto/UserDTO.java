package com.project.teamup.dto;


import com.project.teamup.model.*;
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
    private UserStatus status;
    private UserApprovalStatus approvalStatus;
    private LocationDTO location;
    private CompanyDTO company;
    private List<UserSkillDTO> skills;
    private List<ProjectExperienceDTO> projectExperiences;
}
