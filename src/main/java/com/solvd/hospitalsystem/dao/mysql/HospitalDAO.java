package com.solvd.hospitalsystem.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.Hospital;
import com.solvd.hospitalsystem.Runner;
import com.solvd.hospitalsystem.dao.IHospitalDAO;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;
import com.solvd.hospitalsystem.utils.Connection;


public class HospitalDAO<T> extends AbstractMySQLDAO<T> implements IHospitalDAO<T>{

	final static Logger logger = LogManager.getLogger(Runner.class.getName());

	private Connection conn;

	public HospitalDAO(String url, String username, String password) throws SQLException {
		super(url, username, password);
	}

	@Override
	public T getEntityById(long id) throws SQLException, InterruptedException {
		String query = "SELECT * FROM HospitalSystem.Hospital WHERE id = ?";
		Connection conn = ConnectionPoolA.getInstance().getConnection();
		try {
			PreparedStatement statement = getConnection().prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String hospital_name = result.getString("hospital_name");
				String address = result.getString("address");
				return (T) new Hospital(id, hospital_name, address);
			}
		}catch (SQLException e) {
			logger.error(e);
		}finally {
			conn.close();
		}
		return null;
	}

	public List<T> getAllHospitals() throws SQLException {
		String query = "SELECT * FROM HospitalSystem.Hospital";
		PreparedStatement statement = getConnection().prepareStatement(query);		
		ResultSet result = statement.executeQuery(query);
		List<T> hospitals = new ArrayList<>();
		while (result.next()) {
			long id = result.getLong("id");
			String hospital_name = result.getString("hospital_name");
			String address = result.getString("address");
			hospitals.add((T) new Hospital(id, hospital_name, address));
		}
		return hospitals;
	}

	@Override
	public void updateEntity(T entity) {
		Hospital hospital = (Hospital) entity;
		try (PreparedStatement statement = getConnection().prepareStatement("UPDATE hospital SET WHERE id = ? hospital_name = ?, address = ?")) {
			statement.setLong(1, hospital.getId());
			statement.setString(2, hospital.getHospital_name());
			statement.setString(3, hospital.getAddress());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	@Override
	public T createEntity(T entity) {
		Hospital hospital = (Hospital) entity;
		try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO hospital (hospital_name, address) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, hospital.getHospital_name());
			statement.setString(2, hospital.getAddress());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				hospital.setId(resultSet.getLong(1));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return (T) hospital;
	}

	@Override
	public void removeEntity(long id) {
		try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM hospital WHERE id = ?")) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}

