package com.project.teamup.service;


import com.project.teamup.dao.TechnologyRepository;
import com.project.teamup.model.Technology;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {
    @Autowired
    private TechnologyRepository technologyRepository;

    public List<Technology> getAll() {
        return technologyRepository.findAll();
    }

    public Technology save(@Valid Technology technology) throws ObjectNotFoundException {
        return technologyRepository.save(technology);
    }

    public void delete(Long id) {
        technologyRepository.deleteById(id);
    }

    public Optional<Technology> findById(Long id) {
        return technologyRepository.findById(id);
    }
}
