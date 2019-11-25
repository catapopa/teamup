package com.project.teamup.service.validator;

import com.project.teamup.model.Technology;
import com.project.teamup.model.TechnologyArea;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(MockitoJUnitRunner.class)
public class TechnologyValidatorTest {
    @InjectMocks
    private TechnologyValidator technologyValidator;
    @Mock
    private TechnologyAreaValidator technologyAreaValidator;


    @Before
    public void setUp(){
    }

    @Test
    public void validateObject() {
        TechnologyArea technologyArea =
                new TechnologyArea(1L,"test");
        Technology technology =
                new Technology(1L,"cK3I4v4wUaez17hit6btntLLkobJgrPjm2k25SECISPbvGdXjXHUdRo",technologyArea);
        // case: technologyArea is not valid
        Mockito.when(technologyAreaValidator.validateObject(technologyArea)).thenReturn(false);
        Assert.assertFalse(technologyValidator.validateObject(technology));

        // case: technologyArea is valid but technology.name.length > 50
        Assert.assertFalse(technologyValidator.validateObject(technology));

        // edge case: technology.name.length = 50
        Mockito.when(technologyAreaValidator.validateObject(technologyArea)).thenReturn(true);
        technology.setName("cK3I4v4wUaez17hit6btntLLkobJgrPjm2k25SECISPbvGdXjX");
        Assert.assertTrue(technologyValidator.validateObject(technology));

        // case: technology.name.length < 50
        technology.setName("test");
        Assert.assertTrue(technologyValidator.validateObject(technology));
    }
}