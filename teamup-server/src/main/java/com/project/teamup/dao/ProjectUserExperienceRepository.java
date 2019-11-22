package com.project.teamup.dao;


import com.project.teamup.model.ProjectUserExperience;
import com.project.teamup.model.ProjectUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserExperienceRepository extends JpaRepository<ProjectUserExperience, ProjectUserId> {
}
