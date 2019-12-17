package com.project.teamup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technology_areas")
public class TechnologyArea {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(targetClass = String.class)
    @MapKeyColumn(name = "language")
    @CollectionTable(name = "technology_area_translations",
            joinColumns = @JoinColumn(name = "technology_area_id", referencedColumnName = "id"))
    private Map<UserLanguage, String> translations;
}
