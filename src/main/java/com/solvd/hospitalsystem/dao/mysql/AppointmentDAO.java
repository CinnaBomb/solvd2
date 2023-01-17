package com.solvd.hospitalsystem.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.solvd.hospitalsystem.appointment.Appointment;
import com.solvd.hospitalsystem.dao.IAppointmentDAO;
import com.solvd.hospitalsystem.utils.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class AppointmentDAO<T> extends AbstractMySQLDAO<T> implements IAppointmentDAO<T> {

	public AppointmentDAO(String url, String username, String password) throws SQLException {
		super(url, username, password);
	}

	@Override
	public T getEntityById(long id) throws SQLException, InterruptedException {
		String query = "SELECT * FROM HospitalSystem.Appointment WHERE id = ?";
		Connection conn = ConnectionPoolA.getInstance().getConnection();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (T) new Appointment(resultSet.getLong("id"),
						resultSet.getDate("appointment_date"),
						resultSet.getTimestamp("appointment_time"),
						resultSet.getString("treatment_notes"),
						resultSet.getLong("room_id"),
						resultSet.getLong("employee_id"),
						resultSet.getLong("patient_id"));
			}
		}catch (SQLException e) {
			logger.error(e);
		}finally {
			conn.close();
		}
		return null;
	}

	@Override
	public List<T> getAllAppointments() throws SQLException {
		String query = "SELECT * FROM HospitalSystem.Appointment";
		List<T> appointments = new ArrayList<>();
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				appointments.add((T) new Appointment(resultSet.getLong("id"),
						resultSet.getDate("appointment_date"),
						resultSet.getTimestamp("appointment_time"),
						resultSet.getString("treatment_notes"),
						resultSet.getLong("room_id"),
						resultSet.getLong("employee_id"),
						resultSet.getLong("patient_id")));
			}
		}
		return appointments;
	}

	@Override
	public void updateEntity(T entity) {
		Appointment appointment = (Appointment) entity;
		String query = "UPDATE HospitalSystem.Appointment SET appointment_date = ?, appointment_time = ?, treatment_notes = ?, room_id = ?, employee_id = ?, patient_id = ? WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setDate(1, appointment.getAppointment_date());
			statement.setTimestamp(2, appointment.getAppointment_time());
			statement.setString(3, appointment.getTreatment_notes());
			statement.setLong(4, appointment.getRoom_id());
			statement.setLong(5, appointment.getEmployee_id());
			statement.setLong(6, appointment.getPatient_id());
			statement.setLong(7, appointment.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	@Override
	public T createEntity(T entity) {
		Appointment appointment = (Appointment) entity;
		String query = "INSERT INTO HospitalSystem.Appointment (appointment_date, appointment_time, treatment_notes, room_id, employee_id, patient_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setDate(1, appointment.getAppointment_date());
			statement.setTimestamp(2, appointment.getAppointment_time());
			statement.setString(3, appointment.getTreatment_notes());
			statement.setLong(4, appointment.getRoom_id());
			statement.setLong(5, appointment.getEmployee_id());
			statement.setLong(6, appointment.getPatient_id());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				appointment.setId(resultSet.getLong(1));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return (T) appointment;
	}

	@Override
	public void removeEntity(long id) {
		String query = "DELETE FROM appointment WHERE id = ?";
		try (PreparedStatement statement = getConnection().prepareStatement(query)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}

