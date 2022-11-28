package com.nhs.exercise.restfulservicesnhs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Skill {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min = 2)
	private String description;

	@OneToMany(mappedBy = "skill")
	@JsonIgnore
	private List<UserSkillsMapping> userSkillsMappingList; 
	
	public Skill() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", description=" + description  + "]";
	}

	public List<UserSkillsMapping> getUserSkillsMappingList() {
		return userSkillsMappingList;
	}

	public void setUserSkillsMappingList(List<UserSkillsMapping> userSkillsMappingList) {
		this.userSkillsMappingList = userSkillsMappingList;
	}

}
