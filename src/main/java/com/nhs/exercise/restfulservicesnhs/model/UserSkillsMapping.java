package com.nhs.exercise.restfulservicesnhs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserSkillsMapping {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@ManyToOne
//	@JsonIgnore
	private UserBean user;

	@ManyToOne
//	@JsonIgnore
	private Skill skill;

	@Column
	private LEVELS level;

	public UserSkillsMapping() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public LEVELS getLevel() {
		return level;
	}

	public void setLevel(LEVELS level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "UserSkillsMapping [id=" + id + ", user=" + user + ", level=" + level + "]";
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
