package com.nhs.exercise.restfulservicesnhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.nhs.exercise.restfulservicesnhs.dao.SkillRepository;
import com.nhs.exercise.restfulservicesnhs.exception.UserNotFoundException;
import com.nhs.exercise.restfulservicesnhs.model.Skill;

import jakarta.validation.Valid;

/**
 * @author Nitin
 * RestService to expose API endpoints for maintaining Skills 
 *
 */
@RestController
public class SkillMService {
	
	@Autowired
	private SkillRepository skillRepository;
	

	@GetMapping(path = "nhsService/getAllSkills")
	public List<Skill> getAllSkills() {
		List<Skill> skillList = skillRepository.findAll();
		return skillList;
	}
	
	@GetMapping(path = "nhsService/getSkill/{id}")
	public Skill retrieveSkill(@PathVariable long id) {
		Skill skill = skillRepository.findById(id).orElse(null);
		if(skill == null) {
			throw new UserNotFoundException("Skill not found");
		}
		return skill;
	}
	
	@PostMapping(path = "nhsService/createSkill")
	public void createSkill(@Valid @RequestBody Skill skill) {
		if(skill.getId() != null) {
			Skill checkSkill = skillRepository.findById(skill.getId()).orElse(null);
			if(checkSkill != null) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Skill already present");
			}
		}
		skillRepository.save(skill);
	}

	@DeleteMapping(path = "nhsService/deleteSkill/{id}")
	public void deleteSkill(@PathVariable long id) {
		skillRepository.deleteById(id);
	}
	
	@PostMapping(path = "nhsService/updateSkill")
	public void updateSkill(@Valid @RequestBody Skill skill) {
		Skill existingSkill = skillRepository.findById(skill.getId()).orElse(null);
		if(existingSkill == null) {
			throw new UserNotFoundException("Skill not found to be modified");
		}
		skillRepository.save(skill);
	}

}
