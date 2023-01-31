package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Equipment")
@XmlRootElement(name = "Equipment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipment extends Model{

	@JsonProperty("equipmentName")
	@XmlElement
    private String equipmentName;
	
	@JsonProperty("equipmentType")
	@XmlElement
    private String equipmentType;
	
	@JsonProperty("serialNumber")
	@XmlElement
    private String serialNumber;
	
	@JsonProperty("roomId")
	@XmlElement
    private long roomId;

    public Equipment() {
    	super();
    }
	public Equipment(long id, String equipmentName, String equipmentType, String serialNumber, long roomId) {
		super(id);
		this.equipmentName = equipmentName;
		this.equipmentType = equipmentType;
		this.serialNumber = serialNumber;
		this.roomId = roomId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

    
}
