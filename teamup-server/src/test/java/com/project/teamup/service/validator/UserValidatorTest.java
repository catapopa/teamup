package com.project.teamup.service.validator;

import com.project.teamup.model.Technology;
import com.project.teamup.model.User;
import com.project.teamup.model.UserSkill;
import com.project.teamup.model.UserSkillLevel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
    @InjectMocks
    UserValidator userValidator;
    @Mock
    TechnologyValidator technologyValidator;

    @Test
    public void validateUserSkills() {
        List<UserSkill> userSkills = new ArrayList<>();
        UserSkill userSkill = new UserSkill(1L,null,new Technology(), UserSkillLevel.BEGINNER);
        userSkills.add(userSkill);

        // case: user is null
        Assert.assertFalse(userValidator.validateUserSkills(userSkills));

        // case: technology is not valid
        userSkills.get(0).setUser(new User());
        Mockito.when(technologyValidator.validateObject(Mockito.any())).thenReturn(false);
        Assert.assertFalse(userValidator.validateUserSkills(userSkills));

        // case: technology is valid
        Mockito.when(technologyValidator.validateObject(Mockito.any())).thenReturn(true);
        Assert.assertTrue(userValidator.validateUserSkills(userSkills));
    }
}