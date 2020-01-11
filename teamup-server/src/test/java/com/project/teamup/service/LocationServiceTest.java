package com.project.teamup.service;

import com.project.teamup.dao.LocationRepository;
import com.project.teamup.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Junit Tests for the Location Service.
 * Location Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    private Location location1;
    private Location location2;

    @Test
    public void getAll() {
        when(locationRepository.findAll()).thenReturn(null);
        assertNull(locationService.getAll());
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        when(locationRepository.findAll()).thenReturn(locations);
        assertEquals(2, locationService.getAll().size());

        Location location3 = new Location();
        locations.add(location3);
        when(locationRepository.findAll()).thenReturn(locations);
        assertNotEquals(2, locationService.getAll().size());
    }

    @Test
    public void findById() {
        when(locationRepository.findById(3L)).thenReturn(Optional.empty());
        location2 = new Location();
        location2.setId(2L);
        location2.setCity("Cluj-Napoca");
        location2.setCountry("Romania");
        when(locationRepository.findById(2L)).thenReturn(Optional.of(location2));

        assertTrue(locationService.findById(2L).isPresent());
        assertEquals(location2.getCity(), locationService.findById(2L).get().getCity());
        assertFalse(locationService.findById(3L).isPresent());
    }

    @Test
    public void save() {
        location1 = new Location();
        location1.setId(1L);
        location1.setCountry("Romania");

        location2 = new Location();
        location2.setCity("Cluj-Napoca");
        when(locationRepository.save(location1)).thenReturn(location1);
        when(locationRepository.save(location2)).thenReturn(location2);
        assertEquals("Romania", locationService.save(location1).getCountry());
        assertEquals("Cluj-Napoca", locationService.save(location2).getCity());
    }

    @Test
    public void delete() {
        locationService.delete(1L);
        locationService.delete(2L);
        Mockito.verify(locationRepository).deleteById(1L);
        Mockito.verify(locationRepository, times(2)).deleteById(any());
    }
}