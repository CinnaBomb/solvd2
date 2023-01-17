package com.solvd.hospitalsystem.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.solvd.hospitalsystem.appointment.Symptom;
import com.solvd.hospitalsystem.dao.ISymptomDAO;
import com.solvd.hospitalsystem.utils.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class SymptomDAO<T> extends AbstractMySQLDAO<T> implements ISymptomDAO<T> {

	public SymptomDAO(String url, String username, String password) throws SQLException {
		super(url, username, password);
	}

	@Override
	public T getEntityById(long id) throws SQLException, InterruptedException {
		String query = "SELECT * FROM HospitalSystem.Symptom WHERE id = ?";
		Connection conn = ConnectionPoolA.getInstance().getConnection();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (T) new Symptom(resultSet.getLong("id"),
						resultSet.getString("symptom_name"));
			}
		}catch (SQLException e) {
			logger.error(e);
		}finally {
			conn.close();
		}
		return null;
	}

	@Override
	public List<T> getAllSymptoms() throws SQLException {
		String query = "SELECT * FROM HospitalSystem.Symptom";
		List<T> symptoms = new ArrayList<>();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				symptoms.add((T) new Symptom(resultSet.getLong("id"),
						resultSet.getString("symptom_name")));
			}
		}
		return symptoms;
	}

	@Override
	public void updateEntity(T entity) {
		Symptom symptom = (Symptom) entity;
		String query = "UPDATE HospitalSystem.Symptom SET symptom_name = ? WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setString(1, symptom.getSymptom_name());
			statement.setLong(2, symptom.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	@Override
	public T createEntity(T entity) {
		Symptom symptom = (Symptom) entity;
		String query = "INSERT INTO HospitalSystem.Symptom (symptom_name) VALUES (?)";
		try (PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, symptom.getSymptom_name());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				symptom.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return (T) symptom;
	}

	@Override
	public void removeEntity(long id) {
		String query = "DELETE FROM HospitalSystem.Symptom WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}


