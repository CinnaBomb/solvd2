package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IAppointmentSymptomDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;
import com.solvd.hospitalsystem.services.HospitalRunner;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class AppointmentSymptomDAO extends MySQLDAO<AppointmentSymptom> implements IAppointmentSymptomDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());
	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public AppointmentSymptom getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentSymptom WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				AppointmentSymptom apptSymptom = resultSetToAppointmentSymptom(rs);
				return apptSymptom;
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
	public void updateEntity(AppointmentSymptom entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE AppointmentSymptom SET symptomName = ?, severity = ?, appointmentId = ? WHERE id = ?");
			statement.setString(1, entity.getSymptomName());
			statement.setString(2, entity.getSeverity());
			statement.setLong(3, entity.getAppointmentId());
			statement.setLong(4, entity.getId());
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
	public AppointmentSymptom createEntity(AppointmentSymptom entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO AppointmentSymptom (symptomName, severity, appointmentId) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getSymptomName());
			statement.setString(2, entity.getSeverity());
			statement.setLong(3, entity.getAppointmentId());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				entity.setId(rs.getLong(1));
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
		return entity;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("DELETE FROM AppointmentSymptom WHERE id = ?");
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
	public List<AppointmentSymptom> getAllAppointmentSymptoms() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AppointmentSymptom> symptoms = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentSymptom");
			rs = statement.executeQuery();
			while (rs.next()) {
				AppointmentSymptom symptom = resultSetToAppointmentSymptom(rs);
				symptoms.add(symptom);
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
		return symptoms;
	}

	@Override
	public List<AppointmentSymptom> getAppointmentSymptomsByParameter(String parameter, Object value)
			throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AppointmentSymptom> symptoms = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentSymptom WHERE " + parameter + " = ?");
			if (value instanceof String) {
				statement.setString(1, (String) value);
			} else if (value instanceof Long) {
				statement.setLong(1, (Long) value);
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				AppointmentSymptom symptom = resultSetToAppointmentSymptom(rs);
				symptoms.add(symptom);
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
		return symptoms;
	}

	private AppointmentSymptom resultSetToAppointmentSymptom(ResultSet rs) throws SQLException {
		long id = rs.getLong("id");
		String symptomName = rs.getString("symptom_name");
		String severity = rs.getString("severity");
		long appointmentId = rs.getLong("appointment_id");
		return new AppointmentSymptom(id, symptomName, severity, appointmentId);
	}

}
