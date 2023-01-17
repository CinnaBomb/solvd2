package com.solvd.hospitalsystem.dao.mysql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.solvd.hospitalsystem.dao.IPatientDAO;
import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.Room;
import com.solvd.hospitalsystem.utils.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class RoomDAO<T> extends AbstractMySQLDAO<T> implements IRoomDAO<T>  {

	public RoomDAO(String url, String username, String password) throws SQLException {
		super(url, username, password);
	}

	@Override
	public T getEntityById(long id) throws SQLException, InterruptedException {
		String query = "SELECT * FROM HospitalSystem.Room WHERE id = ?";
		Connection conn = ConnectionPoolA.getInstance().getConnection();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (T) new Room(resultSet.getLong("id"),
						resultSet.getString("room_number"),
						resultSet.getString("room_type"),
						resultSet.getString("availability"),
						resultSet.getLong("hospital_id"));
			}
		}catch (SQLException e) {
			logger.error(e);
		}finally {
			conn.close();
		}
		return null;
	}

	@Override
	public List<T> getAllRooms() throws SQLException {
		String query = "SELECT * FROM HospitalSystem.Room";
		List<T> rooms = new ArrayList<>();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				rooms.add((T) new Room(resultSet.getLong("id"),
						resultSet.getString("room_number"),
						resultSet.getString("room_type"),
						resultSet.getString("availability"),
						resultSet.getLong("hospital_id")));
			}
		}
		return rooms;
	}

	@Override
	public void updateEntity(T entity) {
		Room room = (Room) entity;
		String query = "UPDATE HospitalSystem.Room SET room_number = ?, room_type = ?, availability = ?, hospital_id = ? WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setString(1, room.getRoom_number());
			statement.setString(2, room.getRoom_type());
			statement.setString(3, room.getAvailability());
			statement.setLong(4, room.getHospital_id());
			statement.setLong(5, room.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}



	@Override
	public void removeEntity(long id) {
		String query = "DELETE FROM HospitalSystem.Room WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}
