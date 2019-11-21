package com.project.teamup.mapper;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class UserMapper {
    public abstract User toUser(UserDTO userDto);

    public abstract List<User> toUserList(List<UserDTO> userDTOS);

    public abstract UserDTO toUserDto(User user);

    public abstract List<UserDTO> toUserDtoList(List<User> userDTOS);

}
