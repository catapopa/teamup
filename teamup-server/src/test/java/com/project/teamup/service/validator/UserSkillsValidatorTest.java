package com.project.teamup.service.validator;

import com.project.teamup.model.Technology;
import com.project.teamup.model.User;
import com.project.teamup.model.UserSkill;
import com.project.teamup.model.UserSkillLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserSkillsValidatorTest {
    @InjectMocks
    UserSkillsValidator userSkillsValidator;
    @Mock
    TechnologyValidator technologyValidator;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void validateObject() {
        List<UserSkill> userSkills = new ArrayList<>();
        UserSkill userSkill = new UserSkill(1L,null,new Technology(), UserSkillLevel.BEGINNER);
        userSkills.add(userSkill);

        // case: user is null
        Assert.assertFalse(userSkillsValidator.validateObject(userSkills));

        // case: technology is not valid
        userSkills.get(0).setUser(new User());
        Mockito.when(technologyValidator.validateObject(Mockito.any())).thenReturn(false);
        Assert.assertFalse(userSkillsValidator.validateObject(userSkills));
    }
}