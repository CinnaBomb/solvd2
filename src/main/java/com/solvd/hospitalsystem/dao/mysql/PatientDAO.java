package com.solvd.hospitalsystem.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.solvd.hospitalsystem.dao.IPatientDAO;
import com.solvd.hospitalsystem.patient.Patient;
import com.solvd.hospitalsystem.utils.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;


public class PatientDAO<T> extends AbstractMySQLDAO<T> implements IPatientDAO<T> {

	public PatientDAO(String url, String username, String password) throws SQLException {
		super(url, username, password);
	}

	@Override
	public T getEntityById(long id) throws SQLException, InterruptedException {
		String query = "SELECT * FROM HospitalSystem.Patient WHERE id = ?";
		Connection conn = ConnectionPoolA.getInstance().getConnection();

		try {
			PreparedStatement statement = getConnection().prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (T) new Patient(resultSet.getLong("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("date_of_birth"),
						resultSet.getString("phone_number"),
						resultSet.getString("address"));
			}
		}catch (SQLException e) {
			logger.error(e);
		}finally {
			conn.close();
		}
		return null;
	}

	public List<T> getAllPatients() throws SQLException {
		String query = "SELECT * FROM HospitalSystem.Patient";
		List<T> patients = new ArrayList<>();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				patients.add((T) new Patient(resultSet.getLong("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("date_of_birth"),
						resultSet.getString("phone_number"),
						resultSet.getString("address")));
			}
		}
		return patients;
	}

	@Override
	public void updateEntity(T entity) {
		Patient patient = (Patient) entity;
		String query = "UPDATE HospitalSystem.Patient SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ?, address = ? WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setString(1, patient.getFirst_name());
			statement.setString(2, patient.getFirst_name());
			statement.setString(3, patient.getDate_of_birth());
			statement.setString(4, patient.getPhone_number());
			statement.setString(5, patient.getAddress());
			statement.setLong(6, patient.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	@Override
	public T createEntity(T entity) {
		Patient patient = (Patient) entity;
		String query = "INSERT INTO HospitalSystem.Patient (first_name, last_name, date_of_birth, phone_number, address) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, patient.getFirst_name());
			statement.setString(2, patient.getFirst_name());
			statement.setString(3, patient.getDate_of_birth());
			statement.setString(4, patient.getPhone_number());
			statement.setString(5, patient.getAddress());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				patient.setId(resultSet.getLong(1));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return (T) patient;
	}

	@Override
	public void removeEntity(long id) {
		String query = "DELETE FROM HospitalSystem.Patient WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}


}


