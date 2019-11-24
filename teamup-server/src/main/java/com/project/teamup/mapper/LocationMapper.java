package com.project.teamup.mapper;

import com.project.teamup.dto.LocationDTO;
import com.project.teamup.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class LocationMapper {
    public abstract Location toEntity(LocationDTO dto);

    public abstract List<Location> toEntityList(List<LocationDTO> entityDTOS);

    public abstract LocationDTO toDto(Location entity);

    public abstract List<LocationDTO> toDtoList(List<Location> entityList);
}
