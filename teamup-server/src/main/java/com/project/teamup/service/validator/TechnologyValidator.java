package com.project.teamup.service.validator;

import com.project.teamup.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link Technology}
 * @author Sebastian
 */
@Component
public class TechnologyValidator {
    @Autowired
    private TechnologyAreaValidator technologyAreaValidator;

    public boolean validateObject(Technology technology){
        if (technology == null) return false;
        if (technologyAreaValidator.validateObject(technology.getArea())){
            return technology.getName().length() <= 50;
        }
        return false;
    }
}
