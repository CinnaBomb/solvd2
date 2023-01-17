package com.solvd.hospitalsystem.patient;

public class Insurance {

	private long id;
	private String insurance_provider;
	private String phone_number;
	private String coverage_details;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInsurance_provider() {
		return insurance_provider;
	}
	public void setInsurance_provider(String insurance_provider) {
		this.insurance_provider = insurance_provider;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getCoverage_details() {
		return coverage_details;
	}
	public void setCoverage_details(String coverage_details) {
		this.coverage_details = coverage_details;
	}



}
