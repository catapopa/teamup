package com.project.teamup.mapper;


import com.project.teamup.dto.SkillDTO;
import com.project.teamup.model.Skill;
import com.project.teamup.model.UserSkill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = TechnologyMapper.class)
public abstract class SkillMapper {
    public abstract Skill toEntity(SkillDTO dto);

    public abstract List<Skill> toEntityList(List<SkillDTO> dtoList);

    public abstract SkillDTO toDto(Skill entity);

    public abstract List<SkillDTO> toDtoList(List<Skill> entityList);
}
