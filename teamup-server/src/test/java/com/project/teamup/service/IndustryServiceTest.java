package com.project.teamup.service;

import com.project.teamup.dao.IndustryRepository;
import com.project.teamup.model.Industry;
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
 * Junit Tests for the Industry Service.
 * Industry Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class IndustryServiceTest {

    @InjectMocks
    private IndustryService industryService;

    @Mock
    private IndustryRepository industryRepository;

    private Industry industry1;
    private Industry industry2;

    @Test
    public void getAll() {
        when(industryRepository.findAll()).thenReturn(null);
        assertNull(industryService.getAll());
        List<Industry> industries = new ArrayList<>();
        industries.add(industry1);
        industries.add(industry2);
        when(industryRepository.findAll()).thenReturn(industries);
        assertEquals(2, industryService.getAll().size());

        Industry industry3 = new Industry(1L, "industry");
        industries.add(industry3);
        assertNotEquals(2, industryService.getAll().size());
    }

    @Test
    public void findById() {
        when(industryRepository.findById(3L)).thenReturn(Optional.empty());
        industry2 = new Industry();
        industry2.setId(2L);
        industry2.setName("Automotive");
        when(industryRepository.findById(2L)).thenReturn(Optional.of(industry2));

        assertTrue(industryService.findById(2L).isPresent());
        assertEquals(industry2.getId(), industryService.findById(2L).get().getId());
        assertFalse(industryService.findById(3L).isPresent());
    }

    @Test
    public void save() {
        industry1 = new Industry();
        industry1.setId(1L);
        industry1.setName("Finance");

        industry2 = new Industry();
        industry2.setId(2L);
        industry2.setName("Automotive");
        when(industryRepository.save(industry1)).thenReturn(industry1);
        when(industryRepository.save(industry2)).thenReturn(industry2);
        assertEquals("Finance", industryService.save(industry1).getName());
        assertEquals(industry2.getId(), industryService.save(industry2).getId());
    }

    @Test
    public void delete() {
        industryService.delete(1L);
        industryService.delete(2L);
        Mockito.verify(industryRepository).deleteById(1L);
        Mockito.verify(industryRepository, times(2)).deleteById(any());
    }
}