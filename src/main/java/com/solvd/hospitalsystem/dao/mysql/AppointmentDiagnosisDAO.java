package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IAppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class AppointmentDiagnosisDAO extends MySQLDAO<AppointmentDiagnosis> implements IAppointmentDiagnosisDAO {

	final Logger logger = LogManager.getLogger(AppointmentDiagnosisDAO.class.getName());

	private final ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public List<AppointmentDiagnosis> getAllAppointmentDiagnoses() throws InterruptedException {
		Connection connection = null;
		List<AppointmentDiagnosis> appointmentDiagnoses = new ArrayList<>();
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM AppointmentDiagnosis", 0);
			rs = statement.executeQuery();
			while (rs.next()) {
				appointmentDiagnoses.add(resultSetToAppointmentDiagnosis(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return appointmentDiagnoses;
	}

	@Override
	public List<AppointmentDiagnosis> getAppointmentDiagnosesByParameter(String parameterName, Object parameterValue)
			throws InterruptedException {
		Connection connection = null;
		List<AppointmentDiagnosis> appointmentDiagnoses = new ArrayList<>();
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM AppointmentDiagnosis WHERE " + parameterName + " = ?", 0);
			statement.setObject(1, parameterValue);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				appointmentDiagnoses.add(resultSetToAppointmentDiagnosis(rs));
			}
			rs.close();

		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return appointmentDiagnoses;
	}

	@Override
	public AppointmentDiagnosis getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM AppointmentDiagnosis WHERE id = ?",
					0);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				AppointmentDiagnosis apptDiagnosis = resultSetToAppointmentDiagnosis(rs);
				return apptDiagnosis;
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
	public void updateEntity(AppointmentDiagnosis entity) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE AppointmentDiagnosis SET diagnosis_name = ?, details = ?, appointment_id = ? WHERE id = ?");
			statement.setString(1, entity.getDiagnosisName());
			statement.setString(2, entity.getDetails());
			statement.setLong(3, entity.getAppointmentId());
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
	public AppointmentDiagnosis createEntity(AppointmentDiagnosis entity) throws InterruptedException {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO AppointmentDiagnosis (diagnosis_name, details, appointment_id) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getDiagnosisName());
			statement.setString(2, entity.getDetails());
			statement.setLong(3, entity.getAppointmentId());
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM AppointmentDiagnosis WHERE id = ?");
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

	private AppointmentDiagnosis resultSetToAppointmentDiagnosis(ResultSet result) {
		try {
			long id = result.getLong("id");
			String diagnosisName = result.getString("diagnosis_name");
			String details = result.getString("details");
			long appointmentId = result.getLong("appointment_id");
			return new AppointmentDiagnosis(id, diagnosisName, details, appointmentId);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;
	}

}
