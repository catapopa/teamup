package com.project.teamup.mapper;

import com.project.teamup.dto.CompanyDTO;
import com.project.teamup.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class CompanyMapper {
    public abstract Company toEntity(CompanyDTO dto);

    public abstract List<Company> toEntityList(List<CompanyDTO> entityDTOS);

    public abstract CompanyDTO toDto(Company entity);

    public abstract List<CompanyDTO> toDtoList(List<Company> entityList);
}
