package com.nhs.exercise.restfulservicesnhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhs.exercise.restfulservicesnhs.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
