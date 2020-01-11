package com.project.teamup.service;

import com.project.teamup.dao.TechnologyRepository;
import com.project.teamup.model.Technology;
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
 * Junit Tests for the Technology Service.
 * Technology Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TechnologyServiceTest {

    @InjectMocks
    private TechnologyService technologyService;

    @Mock
    private TechnologyRepository technologyRepository;

    private Technology technology1;
    private Technology technology2;

    @Test
    public void getAll() {
        when(technologyRepository.findAll()).thenReturn(null);
        assertNull(technologyService.getAll());
        List<Technology> technologies = new ArrayList<>();
        technologies.add(technology1);
        technologies.add(technology2);
        when(technologyRepository.findAll()).thenReturn(technologies);
        assertEquals(2, technologyService.getAll().size());

        Technology technology3 = new Technology();
        technologies.add(technology3);
        assertNotEquals(2, technologyService.getAll().size());
    }

    @Test
    public void save() {
        technology1 = new Technology();
        technology1.setId(1L);
        technology1.setName("Java");

        technology2 = new Technology();
        technology2.setId(2L);
        technology2.setName("C#");
        when(technologyRepository.save(technology1)).thenReturn(technology1);
        when(technologyRepository.save(technology2)).thenReturn(technology2);
        assertEquals("Java", technologyService.save(technology1).getName());
        assertEquals(technology2.getId(), technologyService.save(technology2).getId());
    }

    @Test
    public void delete() {
        technologyService.delete(1L);
        technologyService.delete(2L);
        Mockito.verify(technologyRepository).deleteById(1L);
        Mockito.verify(technologyRepository, times(2)).deleteById(any());
    }

    @Test
    public void findById() {
        when(technologyRepository.findById(3L)).thenReturn(Optional.empty());
        technology2 = new Technology();
        technology2.setId(2L);
        technology2.setName("Java");
        when(technologyRepository.findById(2L)).thenReturn(Optional.of(technology2));

        assertTrue(technologyService.findById(2L).isPresent());
        assertEquals(technology2.getName(), technologyService.findById(2L).get().getName());
        assertFalse(technologyService.findById(3L).isPresent());
    }
}