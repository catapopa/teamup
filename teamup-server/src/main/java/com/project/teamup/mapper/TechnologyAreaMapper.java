package com.project.teamup.mapper;


import com.project.teamup.dto.TechnologyAreaDTO;
import com.project.teamup.model.TechnologyArea;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class TechnologyAreaMapper {
    public abstract TechnologyArea toEntity(TechnologyAreaDTO dto);

    public abstract List<TechnologyArea> toEntityList(List<TechnologyAreaDTO> dtoList);

    public abstract TechnologyAreaDTO toDto(TechnologyArea entity);

    public abstract List<TechnologyAreaDTO> toDtoList(List<TechnologyArea> entityList);
}
