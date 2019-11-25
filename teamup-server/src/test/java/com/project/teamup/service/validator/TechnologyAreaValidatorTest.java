package com.project.teamup.service.validator;

import com.project.teamup.model.TechnologyArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TechnologyAreaValidatorTest {
    @Mock
    private TechnologyAreaValidator technologyAreaValidator;

    @BeforeEach
    void setUp() {
        technologyAreaValidator = new TechnologyAreaValidator();
    }

    @Test
    void validateObject() {
        TechnologyArea testObject = null;
        assertFalse(technologyAreaValidator.validateObject(testObject));
        testObject = new TechnologyArea(1L,"test");
        // case: area.length < 50
        assertTrue(technologyAreaValidator.validateObject(testObject));
        // edge case: area.length = 50
        testObject.setName("CAGwTF3gqo592o3Dc8LtDtt8qp5rUzlmDNAJwnysfEIBmFbd6t");
        assertTrue(technologyAreaValidator.validateObject(testObject));
        // case: area.length > 50
        testObject.setName("cK3I4v4wUaez17hit6btntLLkobJgrPjm2k25SECISPbvGdXjXHUdRo");
        assertFalse(technologyAreaValidator.validateObject(testObject));
    }
}