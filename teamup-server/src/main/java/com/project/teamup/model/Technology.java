package com.project.teamup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    @ManyToOne
    @JoinColumn(name = "technology_area")
    @Valid
    private TechnologyArea area;
    @ElementCollection(targetClass = String.class)
    @MapKeyColumn(name = "language")
    @CollectionTable(name = "technology_translations",
            joinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id"))
    private Map<UserLanguage, String> translations;
}
