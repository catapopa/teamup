package com.project.teamup.dao;


import com.project.teamup.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompaniesByName(String companyName);
}
