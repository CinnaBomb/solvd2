package com.solvd.hospitalsystem.patient;

public class PatientAllergy {

    private long id;
    private long patient_id;
    private long allergy_id;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public long getAllergy_id() {
		return allergy_id;
	}
	public void setAllergy_id(long allergy_id) {
		this.allergy_id = allergy_id;
	}


}
