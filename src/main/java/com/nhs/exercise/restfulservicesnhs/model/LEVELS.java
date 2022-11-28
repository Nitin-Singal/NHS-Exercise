package com.nhs.exercise.restfulservicesnhs.model;

public enum LEVELS {

	Expert("E"),
	Practitioner("P"),
	Working("W"),
	Awareness("A");

	private String code;

	LEVELS(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
