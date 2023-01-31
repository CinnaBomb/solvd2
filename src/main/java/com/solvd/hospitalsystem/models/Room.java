package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Room")
@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room extends Model {

	@JsonProperty("roomNumber")
	@XmlElement
	private String roomNumber;

	@JsonProperty("roomType")
	@XmlElement
	private String roomType;

	@JsonProperty("availability")
	@XmlElement
	private String availability;

	@JsonProperty("hospitalId")
	@XmlElement
	private long hospitalId;

	public Room() {
		super();
	}

	public Room(long id, String roomNumber, String roomType, String availability, long hospitalId) {
		super(id);
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.availability = availability;
		this.hospitalId = hospitalId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
}
