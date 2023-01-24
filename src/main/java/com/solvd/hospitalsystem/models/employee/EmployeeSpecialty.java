package com.solvd.hospitalsystem.models.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "EmployeeSpecialty")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSpecialty extends Model{
	
	@XmlElement
    private long employeeId;
	
	@XmlElement
    private long specialtyId;
    
    public EmployeeSpecialty() {
    	super();
    }

	public EmployeeSpecialty(long id, long employeeId, long specialtyId) {
		super(id);
		this.employeeId = employeeId;
		this.specialtyId = specialtyId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(long specialtyId) {
		this.specialtyId = specialtyId;
	}
    
    
}
