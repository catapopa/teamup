package com.project.teamup.controller;

import com.project.teamup.dto.IndustryDTO;
import com.project.teamup.mapper.IndustryMapper;
import com.project.teamup.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Autowired
    private IndustryService industryService;
    @Autowired
    private IndustryMapper industryMapper;

    @PostMapping(value = "/save")
    public IndustryDTO saveIndustry(@RequestBody IndustryDTO industryDTO) {
        return industryMapper.toDto(industryService.save(industryMapper.toEntity(industryDTO)));
    }

    @GetMapping
    public List<IndustryDTO> getAll() {
        return industryMapper.toDtoList(industryService.getAll());
    }

    @GetMapping("/{id}")
    public IndustryDTO findById(@PathVariable("id") Long id) {
        return industryService.findById(id)
                .map(industry -> industryMapper.toDto(industry))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteIndustry(@PathVariable("id") Long id) {
        industryService.delete(id);
    }
}
