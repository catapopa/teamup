package com.project.teamup.service;


import com.project.teamup.dao.TechnologyAreaRepository;
import com.project.teamup.dao.TechnologyRepository;
import com.project.teamup.model.Technology;
import com.project.teamup.model.TechnologyArea;
import com.project.teamup.service.validator.TechnologyAreaValidator;
import com.project.teamup.service.validator.TechnologyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private TechnologyValidator technologyValidator;

    public List<Technology> getAll() {
        return technologyRepository.findAll();
    }

    public Technology save(Technology technology) {
        if (technologyValidator.validateObject(technology)){
            return technologyRepository.save(technology);
        }
        // Should throw Exception instead of returning null?
        return null;
    }

    public void delete(Long id) {
        technologyRepository.deleteById(id);
    }

    public Optional<Technology> findById(Long id) {
        return technologyRepository.findById(id);
    }
}
