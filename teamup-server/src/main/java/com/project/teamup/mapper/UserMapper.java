package com.project.teamup.mapper;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.model.Skill;
import com.project.teamup.model.User;
import com.project.teamup.model.UserSkill;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {ProjectUserExperienceMapper.class})
public abstract class UserMapper {
    @Autowired
    private SkillMapper skillMapper;

    //TODO: Add project user skill mapper.
    @Mapping(target = "skills", ignore = true)
    public abstract User toBaseEntity(UserDTO dto);

    @IterableMapping(qualifiedByName = "finalEntityMapper")
    public abstract List<User> toEntityList(List<UserDTO> dtoList);

    @Mapping(target = "skills", ignore = true)
    public abstract UserDTO toBaseDto(User entity);

    @IterableMapping(qualifiedByName = "finalDTOMapper")
    public abstract List<UserDTO> toDtoList(List<User> entityList);

    @Named(value = "finalEntityMapper")
    public User toEntity(UserDTO dto) {
        User user = toBaseEntity(dto);
        user.setSkills(toUserSkillList(skillMapper.toEntityList(dto.getSkills()), user.getId()));
        return user;
    }

    @Named(value = "finalDTOMapper")
    public UserDTO toDto(User entity) {
        UserDTO dto = toBaseDto(entity);
        dto.setSkills(skillMapper.toDtoList(toSkillList(entity.getSkills())));
        return dto;
    }

    private Skill toSkill(UserSkill userSkill) {
        return userSkill.getSkill();
    }

    private UserSkill toUserSkill(Skill skill, Long userId) {
        UserSkill userSkill = new UserSkill();
        userSkill.getId().setUserId(userId);
        userSkill.setSkill(skill);
        return userSkill;
    }

    private List<Skill> toSkillList(List<UserSkill> userSkills) {
        return userSkills.stream().map(this::toSkill).collect(Collectors.toList());
    }

    private List<UserSkill> toUserSkillList(List<Skill> skills, Long userId) {
        return skills.stream().map(skill -> toUserSkill(skill, userId)).collect(Collectors.toList());
    }
}
