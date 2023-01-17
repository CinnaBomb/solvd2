package com.solvd.hospitalsystem;

public class EmployeeTreatment {
    private long id;
    private String description;
    private long employeeId;
    private long admissiondischargeId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
