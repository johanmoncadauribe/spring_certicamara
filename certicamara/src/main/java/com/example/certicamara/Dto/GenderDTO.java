package com.example.certicamara.Dto;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class GenderDTO {
	
    private String name;
	
	private String gender;
	
	private String probability;
	
	private String count;
	public GenderDTO(String name, String gender, String probability, String count) {
		super();
		this.name = name;
		this.gender = gender;
		this.probability = probability;
		this.count = count;
	}
	
	public GenderDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
