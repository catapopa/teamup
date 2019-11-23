package com.project.teamup.mapper;


import com.project.teamup.dto.UserExperienceDTO;
import com.project.teamup.model.ProjectUserExperience;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ProjectUserExperienceMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
}
