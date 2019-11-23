package com.project.teamup.service;

import com.project.teamup.dao.IndustryRepository;
import com.project.teamup.model.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryService {
    @Autowired
    private IndustryRepository industryRepository;

    public List<Industry> getAll() {
        return industryRepository.findAll();
    }

    public Optional<Industry> findById(Long id) {
        return industryRepository.findById(id);
    }

    public Industry save(Industry industry) {
        return industryRepository.save(industry);
    }

    public void delete(Long id) {
        industryRepository.deleteById(id);
    }
}
