package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract public class Model {

	@JsonProperty("id")
	@XmlElement
	private long id;
	
	public Model() {}

	public Model(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
