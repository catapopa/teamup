package com.project.teamup.controller;


import com.project.teamup.dto.TechnologyDTO;
import com.project.teamup.mapper.TechnologyMapper;
import com.project.teamup.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private TechnologyMapper technologyMapper;

    @PostMapping(value = "/save")
    public TechnologyDTO save(@RequestBody TechnologyDTO technology) {
        return technologyMapper.toDto(technologyService.save(technologyMapper.toEntity(technology)));
    }

    @GetMapping
    public List<TechnologyDTO> getAll() {
        return technologyMapper.toDtoList(technologyService.getAll());
    }

    @GetMapping("/{id}")
    public TechnologyDTO findById(@PathVariable("id") Long id) {
        return technologyService.findById(id)
                .map(technology -> technologyMapper.toDto(technology))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        technologyService.delete(id);
    }
}
