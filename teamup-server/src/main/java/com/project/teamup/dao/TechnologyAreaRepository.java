package com.project.teamup.dao;


import com.project.teamup.model.TechnologyArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyAreaRepository extends JpaRepository<TechnologyArea, Long> {
    Optional<TechnologyArea> findTechnologyAreaByName(String technologyArea);
}
