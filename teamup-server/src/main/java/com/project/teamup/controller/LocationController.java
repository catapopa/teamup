package com.project.teamup.controller;

import com.project.teamup.dto.LocationDTO;
import com.project.teamup.mapper.LocationMapper;
import com.project.teamup.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationMapper locationMapper;

    @PostMapping(value = "/save")
    public LocationDTO saveLocation(@RequestBody LocationDTO locationDTO) {
        return locationMapper.toDto(locationService.save(locationMapper.toEntity(locationDTO)));
    }

    @GetMapping
    public List<LocationDTO> getAll() {
        return locationMapper.toDtoList(locationService.getAll());
    }

    @GetMapping("/{id}")
    public LocationDTO findById(@PathVariable("id") Long id) {
        return locationService.findById(id)
                .map(location -> locationMapper.toDto(location))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteLocation(@PathVariable("id") Long id) {
        locationService.delete(id);
    }
}
