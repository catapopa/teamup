package com.project.teamup.dao;


import com.project.teamup.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<Skill, Long> {
}
