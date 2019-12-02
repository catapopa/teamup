package com.project.teamup.dto;


import com.project.teamup.model.UserLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyAreaDTO {
    private Long id;
    private String name;
    private UserLanguage language;
}
