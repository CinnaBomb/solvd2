package com.solvd.hospitalsystem.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.hospitalsystem.ListAdapter;
import com.solvd.hospitalsystem.ListDeserializer;
import com.solvd.hospitalsystem.ListSerializer;

@JsonRootName("Hospital")
@XmlRootElement(name = "Hospital")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hospital extends Model {

	@JsonProperty("hospitalName")
	@XmlElement
	private String hospitalName;

	@JsonProperty("address")
	@XmlElement
	private String address;

	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("rooms")
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
