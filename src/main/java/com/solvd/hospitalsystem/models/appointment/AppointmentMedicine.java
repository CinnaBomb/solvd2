package com.solvd.hospitalsystem.models.appointment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "AppointmentMedicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentMedicine extends Model {
	
	@XmlElement
	private String medicineName;
	
	@XmlElement
	private long appointmentId;

	public AppointmentMedicine() {
		super();
	}

	public AppointmentMedicine(long id, String medicineName, long appointmentId) {
		super(id);
		this.medicineName = medicineName;
		this.appointmentId = appointmentId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

}
