package com.solvd.hospitalsystem.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.hospitalsystem.ListAdapter;

public class Hospital extends Model{

	@XmlElement
    private String hospitalName;
	
	@XmlElement
    private String address;
	
	@XmlJavaTypeAdapter(ListAdapter.class)
	@XmlElement
    private List<Room> rooms;

    public Hospital() {
    	super();
    }

	public Hospital(long id, String hospitalName, String address, List<Room> rooms) {
		super(id);
		this.hospitalName = hospitalName;
		this.address = address;
		this.rooms = rooms;
	}

	public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



	public List<Room> getRooms() {
		return rooms;
	}



	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
    
}


