package com.solvd.hospitalsystem.models.appointment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "AppointmentDiagnosis")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentDiagnosis extends Model{

	@XmlElement
	private String diagnosisName;
	
	@XmlElement
	private String details;
	
	@XmlElement
	private long appointmentId;
	
	public AppointmentDiagnosis() {
		super();
	}

	public AppointmentDiagnosis(long id, String diagnosisName, String details, long appointmentId) {
		super(id);
		this.diagnosisName = diagnosisName;
		this.details = details;
		this.appointmentId = appointmentId;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}


}
