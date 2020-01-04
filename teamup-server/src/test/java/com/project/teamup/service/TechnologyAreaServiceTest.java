package com.project.teamup.service;

import com.project.teamup.dao.TechnologyAreaRepository;
import com.project.teamup.model.TechnologyArea;
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
 * Junit Tests for the TechnologyArea Service.
 * TechnologyArea Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TechnologyAreaServiceTest {

    @InjectMocks
    private TechnologyAreaService technologyAreaService;

    @Mock
    private TechnologyAreaRepository technologyAreaRepository;

    private TechnologyArea technologyArea1;
    private TechnologyArea technologyArea2;

    @Test
    public void getAll() {
        when(technologyAreaRepository.findAll()).thenReturn(null);
        assertNull(technologyAreaService.getAll());
        List<TechnologyArea> technologyAreas = new ArrayList<>();
        technologyAreas.add(technologyArea1);
        technologyAreas.add(technologyArea2);
        when(technologyAreaRepository.findAll()).thenReturn(technologyAreas);
        assertEquals(2, technologyAreaService.getAll().size());

        TechnologyArea technologyArea3 = new TechnologyArea();
        technologyAreas.add(technologyArea3);
        assertNotEquals(2, technologyAreaService.getAll().size());
    }

    @Test
    public void save() {
        technologyArea1 = new TechnologyArea();
        technologyArea1.setId(1L);
        technologyArea1.setName("Something");

        technologyArea2 = new TechnologyArea();
        technologyArea2.setId(2L);
        technologyArea2.setName("Abc");
        when(technologyAreaRepository.save(technologyArea1)).thenReturn(technologyArea1);
        when(technologyAreaRepository.save(technologyArea2)).thenReturn(technologyArea2);
        assertEquals("Something", technologyAreaService.save(technologyArea1).getName());
        assertEquals(technologyArea2.getId(), technologyAreaService.save(technologyArea2).getId());
    }

    @Test
    public void delete() {
        technologyAreaService.delete(1L);
        technologyAreaService.delete(2L);
        Mockito.verify(technologyAreaRepository).deleteById(1L);
        Mockito.verify(technologyAreaRepository, times(2)).deleteById(any());
    }

    @Test
    public void findById() {
        when(technologyAreaRepository.findById(3L)).thenReturn(Optional.empty());
        technologyArea2 = new TechnologyArea();
        technologyArea2.setId(2L);
        technologyArea2.setName("Something");
        when(technologyAreaRepository.findById(2L)).thenReturn(Optional.of(technologyArea2));

        assertTrue(technologyAreaService.findById(2L).isPresent());
        assertEquals(technologyArea2.getName(), technologyAreaService.findById(2L).get().getName());
        assertFalse(technologyAreaService.findById(3L).isPresent());
    }
}