package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.models.Room;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class RoomDAO extends MySQLDAO<Room> implements IRoomDAO {

	final Logger logger = LogManager.getLogger(RoomDAO.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Room getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Room WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				Room room = resultSetToRoom(rs);
				return room;
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void updateEntity(Room entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Room SET room_number = ?, room_type = ?, availability = ?, hospital_id = ? WHERE id = ?");
			statement.setString(1, entity.getRoomNumber());
			statement.setString(2, entity.getRoomType());
			statement.setString(3, entity.getAvailability());
			statement.setLong(4, entity.getHospitalId());
			statement.setLong(5, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public Room createEntity(Room entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Room (room_number, room_type, availability, hospital_id) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getRoomNumber());
			statement.setString(2, entity.getRoomType());
			statement.setString(3, entity.getAvailability());
			statement.setLong(4, entity.getHospitalId());
			statement.executeUpdate();

			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				entity.setId(id);
				return entity;
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("DELETE FROM Room WHERE id = ?");
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public List<Room> getAllRooms() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Room> rooms = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Room", 0);
			rs = statement.executeQuery();
			while (rs.next()) {
				rooms.add(resultSetToRoom(rs));
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return rooms;
	}

	@Override
	public List<Room> getRoomsByByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Room> rooms = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Room WHERE " + parameter + " = ?", 0);
			statement.setObject(1, value);
			rs = statement.executeQuery();
			while (rs.next()) {
				rooms.add(resultSetToRoom(rs));
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return rooms;
	}

	private Room resultSetToRoom(ResultSet result) throws SQLException {
		long id = result.getLong("id");
		String roomNumber = result.getString("room_number");
		String roomType = result.getString("room_type");
		String availability = result.getString("availability");
		long hospitalId = result.getLong("hospital_id");
		return new Room(id, roomNumber, roomType, availability, hospitalId);
	}

}
