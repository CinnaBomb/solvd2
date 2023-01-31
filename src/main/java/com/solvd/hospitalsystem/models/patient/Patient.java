package com.solvd.hospitalsystem.models.patient;

import java.sql.Date;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.hospitalsystem.DateAdapter;
import com.solvd.hospitalsystem.DateDeserializer;
import com.solvd.hospitalsystem.DateSerializer;
import com.solvd.hospitalsystem.ListDeserializer;
import com.solvd.hospitalsystem.ListSerializer;
import com.solvd.hospitalsystem.models.Person;

@JsonRootName("Patient")
@XmlRootElement(name = "Patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient extends Person {

	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("dateOfBirth")
	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement
	private Date dateOfBirth;

	@JsonProperty("phoneNumber")
	@XmlElement
	private String phoneNumber;

	@JsonProperty("address")
	@XmlElement
	private String address;

	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("patientInsurances")
	@XmlElement
	private List<PatientInsurance> patientInsurances;
	
	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("patientAllergies")
	@XmlElement
	private List<PatientAllergy> patientAllergies;

	public Patient() {
		super();
	}

	public Patient(long id, String firstName, String lastName, Date dateOfBirth, String phoneNumber, String address,
			List<PatientInsurance> patientInsurances, List<PatientAllergy> patientAllergies) {
		super(id, firstName, lastName);
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.patientInsurances = patientInsurances;
		this.patientAllergies = patientAllergies;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PatientInsurance> getPatientInsurances() {
		return patientInsurances;
	}

	public void setPatientInsurances(List<PatientInsurance> patientInsurances) {
		this.patientInsurances = patientInsurances;
	}

	public List<PatientAllergy> getPatientAllergies() {
		return patientAllergies;
	}

	public void setPatientAllergies(List<PatientAllergy> patientAllergies) {
		this.patientAllergies = patientAllergies;
	}

}
