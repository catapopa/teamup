package com.project.teamup.mapper;

import com.project.teamup.dto.IndustryDTO;
import com.project.teamup.model.Industry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class IndustryMapper {
    public abstract Industry toEntity(IndustryDTO dto);

    public abstract List<Industry> toEntityList(List<IndustryDTO> entityDTOS);

    public abstract IndustryDTO toDto(Industry entity);

    public abstract List<IndustryDTO> toDtoList(List<Industry> entityList);
}
