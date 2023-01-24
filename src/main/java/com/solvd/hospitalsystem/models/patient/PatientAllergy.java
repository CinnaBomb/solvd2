package com.solvd.hospitalsystem.models.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "PatientAllergy")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientAllergy extends Model{

	@XmlElement
    private String allergyName;
	
	@XmlElement
    private String allergyDetails;
	
	@XmlElement
    private long patientId;
    
    public PatientAllergy() {
    	super();
    }
	
	public PatientAllergy(long id, String allergyName, String allergyDetails, long patientId) {
		super(id);
		this.allergyName = allergyName;
		this.allergyDetails = allergyDetails;
		this.patientId = patientId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyDetails() {
		return allergyDetails;
	}
	public void setAllergyDetails(String allergyDetails) {
		this.allergyDetails = allergyDetails;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}



}
