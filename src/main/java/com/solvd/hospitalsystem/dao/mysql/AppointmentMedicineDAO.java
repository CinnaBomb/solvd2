package com.solvd.hospitalsystem.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IAppointmentMedicineDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.services.HospitalRunner;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class AppointmentMedicineDAO extends MySQLDAO<AppointmentMedicine> implements IAppointmentMedicineDAO {

	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	public AppointmentMedicine getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentMedicine WHERE id = ?", 0);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				AppointmentMedicine apptMed = resultSetToAppointmentMedicine(result);
				return apptMed;
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

	public void updateEntity(AppointmentMedicine entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE AppointmentMedicine SET medicineName = ?, appointmentId = ? WHERE id = ?");
			statement.setString(1, entity.getMedicineName());
			statement.setLong(2, entity.getAppointmentId());
			statement.setLong(3, entity.getId());
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

	public AppointmentMedicine createEntity(AppointmentMedicine entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO AppointmentMedicine(medicineName, appointmentId) VALUES(?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getMedicineName());
			statement.setLong(2, entity.getAppointmentId());

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

	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("DELETE FROM AppointmentMedicine WHERE id = ?", 0);
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

	private AppointmentMedicine resultSetToAppointmentMedicine(ResultSet rs) {
		try {
			long id = rs.getLong("id");
			String medicineName = rs.getString("medicineName");
			long appointmentId = rs.getLong("appointmentId");
			return new AppointmentMedicine(id, medicineName, appointmentId);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;
	}

	@Override
	public List<AppointmentMedicine> getAllAppointmentMedicines() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AppointmentMedicine> appointmentMedicines = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentMedicine", 0);

			rs = statement.executeQuery();
			while (rs.next()) {
				AppointmentMedicine appointmentMedicine = resultSetToAppointmentMedicine(rs);
				appointmentMedicines.add(appointmentMedicine);
			}
			return appointmentMedicines;
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
	public List<AppointmentMedicine> getAppointmentMedicinesByParameter(String parameter, Object value)
			throws InterruptedException {
		List<AppointmentMedicine> appointmentMedicines = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM AppointmentMedicine WHERE " + parameter + " = ?", 0);
			if (value instanceof String) {
				statement.setString(1, (String) value);
			} else if (value instanceof Long) {
				statement.setLong(1, (Long) value);
			} else {
				return null;
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				AppointmentMedicine apptMed = resultSetToAppointmentMedicine(rs);
				appointmentMedicines.add(apptMed);
			}
			return appointmentMedicines;
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
}
