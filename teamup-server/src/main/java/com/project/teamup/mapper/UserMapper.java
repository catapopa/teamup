package com.project.teamup.mapper;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = UserSkillMapper.class)
public abstract class UserMapper {
    public abstract User toEntity(UserDTO dto);

    public abstract List<User> toEntityList(List<UserDTO> dtoList);

    public abstract UserDTO toDto(User entity);

    public abstract List<UserDTO> toDtoList(List<User> entityList);

}
