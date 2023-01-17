package com.solvd.hospitalsystem.appointment;

public class AppointmentSymptom {

    private long id;
    private String severity;
    private long symptomId;
    private long appointmentId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public long getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(long symptomId) {
		this.symptomId = symptomId;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

    
}

