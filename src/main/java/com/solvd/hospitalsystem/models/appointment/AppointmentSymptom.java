package com.solvd.hospitalsystem.models.appointment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "AppointmentSymptom")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentSymptom extends Model {
	
	@XmlElement
    private String symptomName;
	
	@XmlElement
    private String severity;
	
	@XmlElement
    private long appointmentId;
    
    public AppointmentSymptom() {
    	super();
    }
    
	public AppointmentSymptom(long id, String symptomName, String severity, long appointmentId) {
		super(id);
		this.symptomName = symptomName;
		this.severity = severity;
		this.appointmentId = appointmentId;
	}
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

    
}

