package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IAppointmentDAO;
import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;
import com.solvd.hospitalsystem.services.HospitalRunner;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class AppointmentDAO extends MySQLDAO<Appointment> implements IAppointmentDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	
	public AppointmentDAO() {
		super();
	}

	private ConnectionPoolA pool = new ConnectionPoolA();

	public Appointment getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Appointment WHERE id = ?", 0);
			statement.setLong(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				Appointment appt = resultSetToAppointment(rs);
				return appt;
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

	public void updateEntity(Appointment entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Appointment SET appointment_date = ?, appointment_time = ?, treatment_notes = ?, room_id = ?, employee_id = ?, patient_id = ?, appointment_symptoms = ?, appointment_diagnoses = ?, appointment_medicines = ? WHERE id = ?");
			statement.setDate(1, entity.getAppointmentDate());
			statement.setTimestamp(2, entity.getAppointmentTime());
			statement.setString(3, entity.getTreatmentNotes());
			statement.setLong(4, entity.getRoomId());
			statement.setLong(5, entity.getEmployeeId());
			statement.setLong(6, entity.getPatientId());
			statement.setObject(7, entity.getAppointmentSymptoms());
			statement.setObject(8, entity.getAppointmentDiagnoses());
			statement.setObject(9, entity.getAppointmentMedicines());
			statement.setLong(10, entity.getId());
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

	public Appointment createEntity(Appointment entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Appointment (appointment_date, appointment_time, treatment_notes, room_id, employee_id, patient_id, appointment_symptoms, appointment_diagnoses, appointment_medicines) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, entity.getAppointmentDate());
			statement.setTimestamp(2, entity.getAppointmentTime());
			statement.setString(3, entity.getTreatmentNotes());
			statement.setLong(4, entity.getRoomId());
			statement.setLong(5, entity.getEmployeeId());
			statement.setLong(6, entity.getPatientId());
			statement.setObject(7, entity.getAppointmentSymptoms());
			statement.setObject(8, entity.getAppointmentDiagnoses());
			statement.setObject(9, entity.getAppointmentMedicines());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				return new Appointment(id, entity.getAppointmentDate(), entity.getAppointmentTime(),
						entity.getTreatmentNotes(), entity.getRoomId(), entity.getEmployeeId(), entity.getPatientId(),
						entity.getAppointmentSymptoms(), entity.getAppointmentDiagnoses(),
						entity.getAppointmentMedicines());
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
			statement = connection.prepareStatement("DELETE FROM Appointment WHERE id = ?");
			statement.setLong(1, id);
			statement.executeQuery();
		} catch (SQLException e) {
			logger.info(e);
		}finally {
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
	public List<Appointment> getAllAppointments() throws InterruptedException {
		List<Appointment> appointments = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Appointment");
			rs = statement.executeQuery();
			while (rs.next()) {
				appointments.add(resultSetToAppointment(rs));
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
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsByParameter(String parameter, Object value) throws InterruptedException {
		List<Appointment> appointments = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM Appointment WHERE " + parameter + " = ?");
			if (value instanceof String) {
				statement.setString(1, (String) value);
			} else if (value instanceof Long) {
				statement.setLong(1, (Long) value);
			} else if (value instanceof Date) {
				statement.setDate(1, (Date) value);
			} else if (value instanceof Timestamp) {
				statement.setTimestamp(1, (Timestamp) value);
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				Appointment appointment = resultSetToAppointment(rs);
				appointments.add(appointment);
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
		return appointments;
	}

	private Appointment resultSetToAppointment(ResultSet rs) throws InterruptedException {
		try {
			long id = rs.getLong("id");
			Date appointmentDate = rs.getDate("appointment_date");
			Timestamp appointmentTime = rs.getTimestamp("appointment_time");
			String treatmentNotes = rs.getString("treatment_notes");
			long roomId = rs.getLong("room_id");
			long employeeId = rs.getLong("employee_id");
			long patientId = rs.getLong("patient_id");
			List<AppointmentSymptom> appointmentSymptoms = getAppointmentSymptoms(id);
			List<AppointmentDiagnosis> appointmentDiagnoses = getAppointmentDiagnoses(id);
			List<AppointmentMedicine> appointmentMedicines = getAppointmentMedicines(id);
			return new Appointment(id, appointmentDate, appointmentTime, treatmentNotes, roomId, employeeId, patientId,
					appointmentSymptoms, appointmentDiagnoses, appointmentMedicines);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;
	}

	private List<AppointmentSymptom> getAppointmentSymptoms(long id) throws InterruptedException {
		List<AppointmentSymptom> symptoms = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM AppointmentSymptom WHERE appointment_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long symptomId = rs.getLong("id");
				String symptomName = rs.getString("symptom_name");
				String severity = rs.getString("severity");
				long appointmentId = rs.getLong("appointment_id");
				symptoms.add(new AppointmentSymptom(symptomId, symptomName, severity, appointmentId));
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

	private List<AppointmentDiagnosis> getAppointmentDiagnoses(long id) throws InterruptedException {
		List<AppointmentDiagnosis> appointmentDiagnoses = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM AppointmentDiagnosis WHERE appointment_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long diagnosisId = rs.getLong("id");
				String diagnosisName = rs.getString("diagnosis_name");
				String details = rs.getString("details");
				appointmentDiagnoses.add(new AppointmentDiagnosis(diagnosisId, diagnosisName, details, id));
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
		return appointmentDiagnoses;
	}

	private List<AppointmentMedicine> getAppointmentMedicines(long id) throws InterruptedException {
		List<AppointmentMedicine> appointmentMedicines = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM Appointment_medicine WHERE appointment_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long medicineId = rs.getLong("id");
				String medicineName = rs.getString("medicine_name");
				AppointmentMedicine appointmentMedicine = new AppointmentMedicine(medicineId, medicineName, id);
				appointmentMedicines.add(appointmentMedicine);
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
		return appointmentMedicines;
	}

}