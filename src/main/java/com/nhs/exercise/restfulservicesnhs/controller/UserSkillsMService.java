package com.nhs.exercise.restfulservicesnhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhs.exercise.restfulservicesnhs.dao.SkillRepository;
import com.nhs.exercise.restfulservicesnhs.dao.UserRepository;
import com.nhs.exercise.restfulservicesnhs.dao.UserSkillsRepository;
import com.nhs.exercise.restfulservicesnhs.exception.UserNotFoundException;
import com.nhs.exercise.restfulservicesnhs.model.Skill;
import com.nhs.exercise.restfulservicesnhs.model.SkillsAndLevelVO;
import com.nhs.exercise.restfulservicesnhs.model.UserBean;
import com.nhs.exercise.restfulservicesnhs.model.UserSkillsMapping;

import jakarta.validation.Valid;

/**
 * @author Nitin 
 * RestService to expose API endpoints for mapping Skills with User
 *
 */
@RestController
public class UserSkillsMService {

	@Autowired
	private UserSkillsRepository userSkillsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping(path = "nhsService/getAllUserSkills")
	public List<UserSkillsMapping> getAllUserSkills() {
		List<UserSkillsMapping> skillList = userSkillsRepository.findAll();
		return skillList;
	}

	@GetMapping(path = "nhsService/user/{id}/getSkill")
	public List<UserSkillsMapping> retrieveSkillsforUser(@PathVariable long id) {

		UserBean user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		List<UserSkillsMapping> skillList = userSkillsRepository.findAll();
		List<UserSkillsMapping> userSkillList = skillList.stream().filter(s -> s.getUser().equals(user)).toList();

		return userSkillList;
	}

	/**
	 * Map skill to the user and throw an exception if user or skill is not present
	 * 
	 * @param id
	 * @param skillAndLevelBean
	 */
	@PostMapping(path = "nhsService/user/{id}/addSkill")
	public void addSkillToUser(@PathVariable long id, @Valid @RequestBody SkillsAndLevelVO skillAndLevel) {

		UserSkillsMapping userSkillsMapping = new UserSkillsMapping();
		UserBean user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User not found");
		}
		userSkillsMapping.setUser(user);

		Skill skill = skillRepository.findById(skillAndLevel.getSkillId()).orElse(null);
		if (skill == null) {
			throw new UserNotFoundException("Skill not found");
		}
		userSkillsMapping.setSkill(skill);
		userSkillsMapping.setLevel(skillAndLevel.getLevel());

		userSkillsRepository.save(userSkillsMapping);
	}

	/**
	 * Remove a particular Skill from the user and throw an error if user and skills
	 * are not found
	 * 
	 * @param id
	 * @param skillAndLevelVO
	 */
	@DeleteMapping(path = "nhsService/user/{id}/removeSkill")
	public void removeSkillFromUser(@PathVariable long id, @Valid @RequestBody SkillsAndLevelVO skillAndLevelVO) {

		// Filter the list for mentioned user and skills
		List<UserSkillsMapping> skillList = userSkillsRepository.findAll();
		UserSkillsMapping userSkillsMapping = skillList.stream().filter(
				s -> s.getUser().getId().equals(id) && s.getSkill().getId().equals(skillAndLevelVO.getSkillId()))
				.findFirst().get();
		if (userSkillsMapping == null) {
			throw new UserNotFoundException("User skills not found to be deleted");
		}

		userSkillsRepository.delete(userSkillsMapping);
	}

}
