package com.project.teamup.mapper;


import com.project.teamup.dto.ProjectExperienceDTO;
import com.project.teamup.dto.UserExperienceDTO;
import com.project.teamup.model.Project;
import com.project.teamup.model.ProjectUserExperience;
import com.project.teamup.model.ProjectUserId;
import com.project.teamup.model.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = {UserMapper.class})
public abstract class ProjectUserExperienceMapper {
    @Autowired
    private ProjectMapper projectMapper;

    public ProjectUserExperience toEntity(UserExperienceDTO dto, Long projectId) {
        ProjectUserExperience projectUserExperience = mapToBase(projectId, dto.getUserId());
        projectUserExperience.setStartDate(dto.getStartDate());
        projectUserExperience.setEndDate(dto.getEndDate());
        projectUserExperience.setDescription(dto.getDescription());
        return projectUserExperience;
    }

    public ProjectUserExperience toEntity(ProjectExperienceDTO dto, Long userId) {
        ProjectUserExperience projectUserExperience = mapToBase(dto.getProject().getId(), userId);
        projectUserExperience.setProject(projectMapper.toEntity(dto.getProject()));
        projectUserExperience.setStartDate(dto.getStartDate());
        projectUserExperience.setEndDate(dto.getEndDate());
        projectUserExperience.setDescription(dto.getDescription());
        return projectUserExperience;
    }

    public UserExperienceDTO mapToUserExperienceDTO(ProjectUserExperience entity) {
        UserExperienceDTO dto = new UserExperienceDTO();
        dto.setUserId(entity.getId().getUserId());
        dto.setFullName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());
        dto.setEmail(entity.getUser().getEmail());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public ProjectExperienceDTO mapToProjectDTO(ProjectUserExperience entity) {
        ProjectExperienceDTO dto = new ProjectExperienceDTO();
        dto.setProject(projectMapper.toDto(entity.getProject()));
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private ProjectUserExperience mapToBase(Long projectId, Long userId) {
        ProjectUserExperience projectUserExperience = new ProjectUserExperience();
        projectUserExperience.setId(new ProjectUserId(projectId, userId));
        User user = new User();
        user.setId(userId);
        projectUserExperience.setUser(user);
        Project project = new Project();
        project.setId(projectId);
        projectUserExperience.setProject(project);
        return projectUserExperience;
    }
}
