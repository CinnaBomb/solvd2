package com.solvd.hospitalsystem.appointment;

import java.sql.Date;
import java.sql.Timestamp;

public class Appointment {

	private long id;
	private Date appointment_date;
	private Timestamp appointment_time;
	private String treatment_notes;
	private long room_id;
	private long employee_id;
	private long patient_id;


	public Appointment(long id, Date appointment_date, Timestamp appointment_time, String treatment_notes, long room_id,
			long employee_id, long patient_id) {
		this.id = id;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.treatment_notes = treatment_notes;
		this.room_id = room_id;
		this.employee_id = employee_id;
		this.patient_id = patient_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	public Timestamp getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(Timestamp appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getTreatment_notes() {
		return treatment_notes;
	}
	public void setTreatment_notes(String treatment_notes) {
		this.treatment_notes = treatment_notes;
	}
	public long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(long room_id) {
		this.room_id = room_id;
	}
	public long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}



}

