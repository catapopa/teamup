package com.project.teamup.service;


import com.project.teamup.dao.TechnologyAreaRepository;
import com.project.teamup.model.TechnologyArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyAreaService {
    @Autowired
    private TechnologyAreaRepository technologyAreaRepository;

    public List<TechnologyArea> getAll() {
        return technologyAreaRepository.findAll();
    }

    public TechnologyArea save(TechnologyArea technology) {
        return technologyAreaRepository.save(technology);
    }

    public void delete(Long id) {
        technologyAreaRepository.deleteById(id);
    }

    public Optional<TechnologyArea> findById(Long id) {
        return technologyAreaRepository.findById(id);
    }
}
