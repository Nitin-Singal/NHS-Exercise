package com.nhs.exercise.restfulservicesnhs.model;

import jakarta.validation.constraints.NotNull;

public class SkillsAndLevelVO {

	@NotNull
	private Long skillId;

	private LEVELS level;

	public SkillsAndLevelVO() {
		super();
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public LEVELS getLevel() {
		return level;
	}

	public void setLevel(LEVELS level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "SkillsAndLevelBean [skillId=" + skillId + ", level=" + level + "]";
	}

}
