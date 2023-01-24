package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EmployeeTreatment")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeTreatment extends Model{

	@XmlElement
	private String description;
	
	@XmlElement
    private long employeeId;
	
	@XmlElement
    private long admissiondischargeId;

    public EmployeeTreatment() {
    	super();
    }
	public EmployeeTreatment(long id, String description, long employeeId, long admissiondischargeId) {
		super(id);
		this.description = description;
		this.employeeId = employeeId;
		this.admissiondischargeId = admissiondischargeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getAdmissiondischargeId() {
		return admissiondischargeId;
	}
	public void setAdmissiondischargeId(long admissiondischargeId) {
		this.admissiondischargeId = admissiondischargeId;
	}

    
}
