package com.solvd.hospitalsystem.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.hospitalsystem.DateAdapter;
import com.solvd.hospitalsystem.TimestampAdapter;

@XmlRootElement(name = "AdmissionDischarge")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdmissionDischarge extends Model{

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement
	private Date admissionDate;
	
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	@XmlElement
	private Timestamp admissionTime;
	
	@XmlElement
	private String reason;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement
	private Date dischargeDate;
	
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	@XmlElement
	private Timestamp dischargeTime;
	
	@XmlElement
	private String dischargeReason;
	
	@XmlElement
	private long patientId;
	
	public AdmissionDischarge() {
		super();
	}

	public AdmissionDischarge(long id, Date admissionDate, Timestamp admissionTime, String reason, Date dischargeDate,
			Timestamp dischargeTime, String dischargeReason, long patientId) {
		super(id);
		this.admissionDate = admissionDate;
		this.admissionTime = admissionTime;
		this.reason = reason;
		this.dischargeDate = dischargeDate;
		this.dischargeTime = dischargeTime;
		this.dischargeReason = dischargeReason;
		this.patientId = patientId;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Timestamp getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(Timestamp admissionTime) {
		this.admissionTime = admissionTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public Timestamp getDischargeTime() {
		return dischargeTime;
	}
	public void setDischargeTime(Timestamp dischargeTime) {
		this.dischargeTime = dischargeTime;
	}
	public String getDischargeReason() {
		return dischargeReason;
	}
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}



}
