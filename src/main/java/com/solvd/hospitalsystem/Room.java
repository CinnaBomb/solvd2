package com.solvd.hospitalsystem;

public class Room {

    private long id;
    private String room_number;
    private String room_type;
    private String availability;
    private long hospital_id;
    
    

    public Room(long id, String room_number, String room_type, String availability, long hospital_id) {
		this.id = id;
		this.room_number = room_number;
		this.room_type = room_type;
		this.availability = availability;
		this.hospital_id = hospital_id;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public long getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(long hospital_id) {
        this.hospital_id = hospital_id;
    }
}

