package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.Room;

public interface IRoomDAO extends IBaseDAO<Room>{
    List<Room> getAllRooms() throws InterruptedException;
	List<Room> getRoomsByByParameter(String parameter, Object value) throws InterruptedException;

}