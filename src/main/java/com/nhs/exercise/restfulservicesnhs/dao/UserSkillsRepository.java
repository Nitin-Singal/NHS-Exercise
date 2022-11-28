package com.nhs.exercise.restfulservicesnhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhs.exercise.restfulservicesnhs.model.UserSkillsMapping;

@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkillsMapping, Long> {

}
