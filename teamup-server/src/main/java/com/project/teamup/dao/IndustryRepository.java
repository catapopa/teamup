package com.project.teamup.dao;


import com.project.teamup.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    Optional<Industry> findIndustryByName(String industryName);
}
