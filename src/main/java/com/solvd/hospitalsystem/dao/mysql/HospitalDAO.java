package com.solvd.hospitalsystem.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IHospitalDAO;
import com.solvd.hospitalsystem.models.Hospital;
import com.solvd.hospitalsystem.models.Room;
import com.solvd.hospitalsystem.services.HospitalRunner;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class HospitalDAO extends MySQLDAO<Hospital> implements IHospitalDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private final ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Hospital getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hospital WHERE id = ?", 0);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Hospital hospital = resultSetToHospital(rs);
				return hospital;
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void updateEntity(Hospital entity) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE Hospital SET hospital_name = ?, address = ?, rooms = ? WHERE id = ?");
			statement.setString(1, entity.getHospitalName());
			statement.setString(2, entity.getAddress());
			statement.setObject(3, entity.getRooms());
			statement.setLong(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public Hospital createEntity(Hospital entity) throws InterruptedException {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Hospital (hospital_name, address, rooms) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getHospitalName());
			statement.setString(2, entity.getAddress());
			statement.setObject(3, entity.getRooms());
			statement.executeUpdate();

			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				entity.setId(id);
				return entity;
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Hospital WHERE id = ?");
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public List<Hospital> getAllHospitals() throws InterruptedException {
		Connection connection = null;
		List<Hospital> hospitals = new ArrayList<>();
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hospital");

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Hospital hospital = resultSetToHospital(rs);
				hospitals.add(hospital);
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return hospitals;
	}

	@Override
	public List<Hospital> getHospitalsByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		List<Hospital> hospitals = new ArrayList<>();
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Hospital WHERE " + parameter + " = ?");
			statement.setObject(1, value);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Hospital hospital = resultSetToHospital(result);
				hospitals.add(hospital);
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return hospitals;
	}

	private Hospital resultSetToHospital(ResultSet rs) {
		try {
			long id = rs.getLong("id");
			String hospitalName = rs.getString("hospital_name");
			String address = rs.getString("address");
			List<Room> rooms = rs.getObject("rooms", ArrayList.class);

			return new Hospital(id, hospitalName, address, rooms);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;
	}

}
