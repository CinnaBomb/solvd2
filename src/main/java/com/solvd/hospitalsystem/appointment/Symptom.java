package com.solvd.hospitalsystem.appointment;

public class Symptom {

	private long id;
	private String symptom_name;
	
	public Symptom(long id, String symptom_name) {
		this.id = id;
		this.symptom_name = symptom_name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSymptom_name() {
		return symptom_name;
	}
	public void setSymptom_name(String symptom_name) {
		this.symptom_name = symptom_name;
	}


}
