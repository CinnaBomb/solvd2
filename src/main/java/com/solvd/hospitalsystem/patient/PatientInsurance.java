package com.solvd.hospitalsystem.patient;

public class PatientInsurance {

    private long id;
    private String policy_number;
    private long patient_id;
    private long insurance_id;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPolicy_number() {
		return policy_number;
	}
	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}
	public long getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(long insurance_id) {
		this.insurance_id = insurance_id;
	}

}

