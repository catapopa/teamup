package com.project.teamup.service;

import com.project.teamup.dao.CompanyRepository;
import com.project.teamup.model.Company;
import org.junit.jupiter.api.Test;
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
 * Junit Tests for the Company Service.
 * Company Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private Company company1;
    private Company company2;

    @Test
    void getAll() {
        when(companyRepository.findAll()).thenReturn(null);
        assertNull(companyService.getAll());
        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        when(companyRepository.findAll()).thenReturn(companies);
        assertEquals(2, companyService.getAll().size());

        Company company3 = new Company();
        companies.add(company3);
        when(companyRepository.findAll()).thenReturn(companies);
        assertNotEquals(2, companyService.getAll().size());
    }

    @Test
    void findById() {
        when(companyRepository.findById(3L)).thenReturn(Optional.empty());
        company2 = new Company();
        company2.setId(2L);
        company2.setName("Ntt Data");
        when(companyRepository.findById(2L)).thenReturn(Optional.of(company2));

        assertTrue(companyService.findById(2L).isPresent());
        assertEquals(company2.getId(), companyService.findById(2L).get().getId());
        assertFalse(companyService.findById(3L).isPresent());
    }

    @Test
    void save() {
        company1 = new Company();
        company1.setId(1L);
        company1.setName("Mhp");

        company2 = new Company();
        company2.setId(2L);
        company2.setName("Ntt Data");
        when(companyRepository.save(company1)).thenReturn(company1);
        when(companyRepository.save(company2)).thenReturn(company2);
        assertEquals("Mhp", companyService.save(company1).getName());
        assertEquals(company2.getId(), companyService.save(company2).getId());
    }

    @Test
    void delete() {
        companyService.delete(3L);
        companyService.delete(1L);
        Mockito.verify(companyRepository).deleteById(1L);
        Mockito.verify(companyRepository, times(2)).deleteById(any());
    }
}