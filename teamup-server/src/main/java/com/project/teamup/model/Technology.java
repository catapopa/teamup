package com.project.teamup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technologies")
public class Technology {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(max = 50)
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private UserLanguage language;
    @ManyToOne
    @JoinColumn(name = "technology_area")
    @Valid
    private TechnologyArea area;
}
