package com.project.teamup.mapper;


import com.project.teamup.dto.UserSkillDTO;
import com.project.teamup.model.UserSkill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = TechnologyMapper.class)
public abstract class UserSkillMapper {
    public abstract UserSkill toEntity(UserSkillDTO dto);

    public abstract List<UserSkill> toEntityList(List<UserSkillDTO> dtoList);

    public abstract UserSkillDTO toDto(UserSkill entity);

    public abstract List<UserSkillDTO> toDtoList(List<UserSkill> entityList);
}
