package com.solvd.hospitalsystem.models.employee;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospitalsystem.models.Person;

@JsonRootName("Employee")
@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person {

	@JsonProperty("phoneNumber")
	@XmlElement
	private String phoneNumber;

	@JsonProperty("role")
	@XmlElement
	private String role;

	@JsonProperty("hospitalId")
	@XmlElement
	private long hospitalId;

	public Employee() {
		super();
	}

	public Employee(long id, String firstName, String lastName, String phoneNumber, String role, long hospitalId) {
		super(id, firstName, lastName);
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.hospitalId = hospitalId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
}
