package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.dao.mysql.RoomDAO;
import com.solvd.hospitalsystem.models.Room;

public class RoomService {
	private IRoomDAO roomDAO;
	
	public RoomService() {
		this.roomDAO = new RoomDAO();
	}

	public Room getRoomById(long id) throws InterruptedException {
		return roomDAO.getEntityById(id);
	}

	public Room createRoom(Room room) throws InterruptedException {
		return roomDAO.createEntity(room);
	}

	public void updateRoom(Room room) throws InterruptedException {
		roomDAO.updateEntity(room);
	}

	public void removeRoom(long id) throws InterruptedException {
		roomDAO.removeEntity(id);
	}

	public List<Room> getAllRooms() throws InterruptedException {
		return roomDAO.getAllRooms();
	}

	public List<Room> getRoomsByParameter(String parameter, Object value) throws InterruptedException {
		return roomDAO.getRoomsByByParameter(parameter, value);
	}
	
	public List<Room> getRoomsByHospitalId(long hospitalId) throws InterruptedException {
		return roomDAO.getRoomsByByParameter("hospital_id", hospitalId);
	}
}
