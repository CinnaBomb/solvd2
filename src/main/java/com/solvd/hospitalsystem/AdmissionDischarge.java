package com.solvd.hospitalsystem;

import java.sql.Date;
import java.sql.Timestamp;

public class AdmissionDischarge {

	private long id;
	private Date admission_date;
	private Timestamp admission_time;
	private String reason;
	private Date discharge_date;
	private Timestamp discharge_time;
	private String discharge_reason;
	private long patient_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getAdmission_date() {
		return admission_date;
	}
	public void setAdmission_date(Date admission_date) {
		this.admission_date = admission_date;
	}
	public Timestamp getAdmission_time() {
		return admission_time;
	}
	public void setAdmission_time(Timestamp admission_time) {
		this.admission_time = admission_time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(Date discharge_date) {
		this.discharge_date = discharge_date;
	}
	public Timestamp getDischarge_time() {
		return discharge_time;
	}
	public void setDischarge_time(Timestamp discharge_time) {
		this.discharge_time = discharge_time;
	}
	public String getDischarge_reason() {
		return discharge_reason;
	}
	public void setDischarge_reason(String discharge_reason) {
		this.discharge_reason = discharge_reason;
	}
	public long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}



}
