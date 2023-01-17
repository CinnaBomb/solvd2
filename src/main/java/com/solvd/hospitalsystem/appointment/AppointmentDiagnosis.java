package com.solvd.hospitalsystem.appointment;

public class AppointmentDiagnosis {

	private long id;
	private String details;
	private long diagnosis_id;
	private long appointment_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public long getDiagnosis_id() {
		return diagnosis_id;
	}
	public void setDiagnosis_id(long diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}
	public long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(long appointment_id) {
		this.appointment_id = appointment_id;
	}


}
