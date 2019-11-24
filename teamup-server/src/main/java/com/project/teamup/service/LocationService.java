package com.project.teamup.service;

import com.project.teamup.dao.LocationRepository;
import com.project.teamup.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
