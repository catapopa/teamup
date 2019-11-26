package com.project.teamup.service.validator;

import com.project.teamup.model.User;
import com.project.teamup.model.UserSkill;
import com.project.teamup.model.UserSkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Please fill out this class with further functions to validate other attributes.
 * @author Sebastian
 */
@Component
public class UserValidator {
    @Autowired
    private TechnologyValidator technologyValidator;

    // Add your validation functions here.
    public boolean validateObject(User user){
        return validateUserSkills(user.getSkills());
    }

    /**
     * Checks the validity of the {@link UserSkill} object.
     * Method is set to 'public' for testing purposes.
     * @param userSkillList is the list of Skills tied to the user.
     * @return true if valid, false if not
     */
    public boolean validateUserSkills(List<UserSkill> userSkillList){
        if (userSkillList != null && userSkillList.size() != 0){
            for (UserSkill userSkill : userSkillList){
                if (userSkill.getUser() == null) return false;
                if (!technologyValidator.validateObject(userSkill.getTechnology())) return false;
                List<UserSkillLevel> userSkillLevelList = Arrays.asList(UserSkillLevel.values());
                if (!userSkillLevelList.contains(userSkill.getLevel())) return false;
            }
            return true;
        }
        return true;
    }
}
