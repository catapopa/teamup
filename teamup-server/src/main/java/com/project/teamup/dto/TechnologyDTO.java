package com.project.teamup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDTO {
    private Long id;
    private String name;
    private TechnologyAreaDTO area;
}
