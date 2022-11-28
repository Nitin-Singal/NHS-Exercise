package com.nhs.exercise.restfulservicesnhs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity(name = "User_Details")
public class UserBean {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min = 2)
	@NotEmpty
	private String name;
	
	@Column
	@Email
	private String email;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserSkillsMapping> userSkillsMappingList;

	public UserBean() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserSkillsMapping> getUserSkillsMappingList() {
		return userSkillsMappingList;
	}

	public void setUserSkillsMappingList(List<UserSkillsMapping> userSkillsMappingList) {
		this.userSkillsMappingList = userSkillsMappingList;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
