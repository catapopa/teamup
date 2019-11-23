package com.project.teamup.mapper;


import com.project.teamup.dto.TechnologyDTO;
import com.project.teamup.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = TechnologyAreaMapper.class)
public abstract class TechnologyMapper {
    public abstract Technology toEntity(TechnologyDTO dto);

    public abstract List<Technology> toEntityList(List<TechnologyDTO> dtoList);

    public abstract TechnologyDTO toDto(Technology entity);

    public abstract List<TechnologyDTO> toDtoList(List<Technology> entityList);
}
