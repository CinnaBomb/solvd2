package com.solvd.hospitalsystem;

public class Hospital {

    private long id;
    private String hospital_name;
    private String address;

	public Hospital(long id, String hospital_name, String address) {
		this.id = id;
		this.hospital_name = hospital_name;
		this.address = address;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


