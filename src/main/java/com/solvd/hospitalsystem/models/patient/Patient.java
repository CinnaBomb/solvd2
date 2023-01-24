package com.solvd.hospitalsystem.models.patient;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.hospitalsystem.DateAdapter;
import com.solvd.hospitalsystem.models.Person;

public class Patient extends Person{

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement
    private Date dateOfBirth;
    
    @XmlElement
    private String phoneNumber;
    
    @XmlElement
    private String address;
    
    @XmlElement
    private List<PatientInsurance> patientInsurances;
    
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
