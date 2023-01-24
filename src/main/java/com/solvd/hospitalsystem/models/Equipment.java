package com.solvd.hospitalsystem.models;

import javax.xml.bind.annotation.XmlElement;

public class Equipment extends Model{

	@XmlElement
    private String equipmentName;
	
	@XmlElement
    private String equipmentType;
	
	@XmlElement
    private String serialNumber;
	
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
