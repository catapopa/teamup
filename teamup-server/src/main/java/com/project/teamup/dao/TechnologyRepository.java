package com.project.teamup.dao;


import com.project.teamup.model.Technology;
import com.project.teamup.model.TechnologyArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findTechnologiesByNameAndArea(String technologyName, TechnologyArea area);
}
