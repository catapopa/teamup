package com.project.teamup.mapper;

import com.project.teamup.dto.CompanyDTO;
import com.project.teamup.dto.IndustryDTO;
import com.project.teamup.dto.LocationDTO;
import com.project.teamup.dto.ProjectDTO;
import com.project.teamup.dto.ProjectExperienceDTO;
import com.project.teamup.dto.UserDTO;
import com.project.teamup.dto.UserExperienceDTO;
import com.project.teamup.model.Company;
import com.project.teamup.model.Industry;
import com.project.teamup.model.Location;
import com.project.teamup.model.Project;
import com.project.teamup.model.ProjectUserExperience;
import com.project.teamup.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Autowired
    private UserSkillMapper userSkillMapper;
    @Autowired
    private ProjectUserExperienceMapper projectUserExperienceMapper;

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setPassword( dto.getPassword() );
        user.setBirthDate( dto.getBirthDate() );
        user.setLocation( locationDTOToLocation( dto.getLocation() ) );
        byte[] picture = dto.getPicture();
        if ( picture != null ) {
            user.setPicture( Arrays.copyOf( picture, picture.length ) );
        }
        user.setCompany( companyDTOToCompany( dto.getCompany() ) );
        user.setRole( dto.getRole() );
        user.setSeniority( dto.getSeniority() );
        user.setLanguage( dto.getLanguage() );
        user.setProjectExperiences( projectExperienceDTOListToProjectUserExperienceList( dto.getProjectExperiences() ) );
        user.setSkills( userSkillMapper.toEntityList( dto.getSkills() ) );

        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDTO userDTO : dtoList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public UserDTO toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( entity.getId() );
        userDTO.setUsername( entity.getUsername() );
        userDTO.setPassword( entity.getPassword() );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setFirstName( entity.getFirstName() );
        userDTO.setLastName( entity.getLastName() );
        userDTO.setBirthDate( entity.getBirthDate() );
        byte[] picture = entity.getPicture();
        if ( picture != null ) {
            userDTO.setPicture( Arrays.copyOf( picture, picture.length ) );
        }
        userDTO.setLanguage( entity.getLanguage() );
        userDTO.setRole( entity.getRole() );
        userDTO.setSeniority( entity.getSeniority() );
        userDTO.setLocation( locationToLocationDTO( entity.getLocation() ) );
        userDTO.setCompany( companyToCompanyDTO( entity.getCompany() ) );
        userDTO.setSkills( userSkillMapper.toDtoList( entity.getSkills() ) );
        userDTO.setProjectExperiences( projectUserExperienceListToProjectExperienceDTOList( entity.getProjectExperiences() ) );

        return userDTO;
    }

    @Override
    public List<UserDTO> toDtoList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    protected Location locationDTOToLocation(LocationDTO locationDTO) {
        if ( locationDTO == null ) {
            return null;
        }

        Location location = new Location();

        location.setId( locationDTO.getId() );
        location.setCountry( locationDTO.getCountry() );
        location.setState( locationDTO.getState() );
        location.setCity( locationDTO.getCity() );

        return location;
    }

    protected Company companyDTOToCompany(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDTO.getId() );
        company.setName( companyDTO.getName() );

        return company;
    }

    protected Industry industryDTOToIndustry(IndustryDTO industryDTO) {
        if ( industryDTO == null ) {
            return null;
        }

        Industry industry = new Industry();

        industry.setId( industryDTO.getId() );
        industry.setName( industryDTO.getName() );

        return industry;
    }

    protected ProjectUserExperience userExperienceDTOToProjectUserExperience(UserExperienceDTO userExperienceDTO) {
        if ( userExperienceDTO == null ) {
            return null;
        }

        ProjectUserExperience projectUserExperience = new ProjectUserExperience();

        projectUserExperience.setStartDate( userExperienceDTO.getStartDate() );
        projectUserExperience.setEndDate( userExperienceDTO.getEndDate() );
        projectUserExperience.setDescription( userExperienceDTO.getDescription() );

        return projectUserExperience;
    }

    protected List<ProjectUserExperience> userExperienceDTOListToProjectUserExperienceList(List<UserExperienceDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectUserExperience> list1 = new ArrayList<ProjectUserExperience>( list.size() );
        for ( UserExperienceDTO userExperienceDTO : list ) {
            list1.add( userExperienceDTOToProjectUserExperience( userExperienceDTO ) );
        }

        return list1;
    }

    protected Project projectDTOToProject(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( projectDTO.getId() );
        project.setName( projectDTO.getName() );
        project.setDescription( projectDTO.getDescription() );
        project.setIndustry( industryDTOToIndustry( projectDTO.getIndustry() ) );
        project.setCompany( companyDTOToCompany( projectDTO.getCompany() ) );
        project.setUserExperiences( userExperienceDTOListToProjectUserExperienceList( projectDTO.getUserExperiences() ) );

        return project;
    }

    protected ProjectUserExperience projectExperienceDTOToProjectUserExperience(ProjectExperienceDTO projectExperienceDTO) {
        if ( projectExperienceDTO == null ) {
            return null;
        }

        ProjectUserExperience projectUserExperience = new ProjectUserExperience();

        projectUserExperience.setProject( projectDTOToProject( projectExperienceDTO.getProject() ) );
        projectUserExperience.setStartDate( projectExperienceDTO.getStartDate() );
        projectUserExperience.setEndDate( projectExperienceDTO.getEndDate() );
        projectUserExperience.setDescription( projectExperienceDTO.getDescription() );

        return projectUserExperience;
    }

    protected List<ProjectUserExperience> projectExperienceDTOListToProjectUserExperienceList(List<ProjectExperienceDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectUserExperience> list1 = new ArrayList<ProjectUserExperience>( list.size() );
        for ( ProjectExperienceDTO projectExperienceDTO : list ) {
            list1.add( projectExperienceDTOToProjectUserExperience( projectExperienceDTO ) );
        }

        return list1;
    }

    protected LocationDTO locationToLocationDTO(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setId( location.getId() );
        locationDTO.setCountry( location.getCountry() );
        locationDTO.setState( location.getState() );
        locationDTO.setCity( location.getCity() );

        return locationDTO;
    }

    protected CompanyDTO companyToCompanyDTO(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId( company.getId() );
        companyDTO.setName( company.getName() );

        return companyDTO;
    }

    protected List<ProjectExperienceDTO> projectUserExperienceListToProjectExperienceDTOList(List<ProjectUserExperience> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectExperienceDTO> list1 = new ArrayList<ProjectExperienceDTO>( list.size() );
        for ( ProjectUserExperience projectUserExperience : list ) {
            list1.add( projectUserExperienceMapper.mapToProjectDTO( projectUserExperience ) );
        }

        return list1;
    }
}
