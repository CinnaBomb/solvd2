package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract public class Person extends Model{

	@JsonProperty("firstName")
	@XmlElement
    private String firstName;
	
	@JsonProperty("lastName")
	@XmlElement
    private String lastName;
    
    public Person() {
    	super();
    }
    
	public Person(long id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    

}
