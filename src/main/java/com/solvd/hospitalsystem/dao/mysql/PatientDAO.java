package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IPatientDAO;
import com.solvd.hospitalsystem.models.patient.Patient;
import com.solvd.hospitalsystem.models.patient.PatientAllergy;
import com.solvd.hospitalsystem.models.patient.PatientInsurance;
import com.solvd.hospitalsystem.services.HospitalRunner;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class PatientDAO extends MySQLDAO<Patient> implements IPatientDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Patient getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Patient WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				Patient patient = resultSetToPatient(rs);
				return patient;
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
	public void updateEntity(Patient entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Patient SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ?, address = ?, patient_insurances = ?, patient_allergies = ? WHERE id = ?");
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, entity.getDateOfBirth());
			statement.setString(4, entity.getPhoneNumber());
			statement.setString(5, entity.getAddress());
			statement.setObject(6, entity.getPatientInsurances());
			statement.setObject(7, entity.getPatientAllergies());
			statement.setLong(8, entity.getId());
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
	public Patient createEntity(Patient entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Patient (first_name, last_name, date_of_birth, phone_number, address, patient_insurances, patient_allergies) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, entity.getDateOfBirth());
			statement.setString(4, entity.getPhoneNumber());
			statement.setString(5, entity.getAddress());
			statement.setObject(6, entity.getPatientInsurances());
			statement.setObject(7, entity.getPatientAllergies());
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
			statement = connection.prepareStatement("DELETE FROM Patient WHERE id = ?");
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
	public List<Patient> getAllPatients() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Patient");
			rs = statement.executeQuery();
			while (rs.next()) {
				Patient patient = resultSetToPatient(rs);
				patients.add(patient);
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
		return patients;
	}

	public List<Patient> getPatientByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Patient WHERE " + parameter + " = ?");
			statement.setObject(1, value);
			rs = statement.executeQuery();
			while (rs.next()) {
				Patient patient = resultSetToPatient(rs);
				patients.add(patient);
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
		return patients;
	}

	private Patient resultSetToPatient(ResultSet rs) throws InterruptedException {
		try {
			long id = rs.getLong("id");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			java.sql.Date dateOfBirth = rs.getDate("dateOfBirth");
			String phoneNumber = rs.getString("phoneNumber");
			String address = rs.getString("address");
			List<PatientInsurance> patientInsurances = getPatientInsurances(id);
			List<PatientAllergy> patientAllergies = getPatientAllergies(id);
			return new Patient(id, firstName, lastName, dateOfBirth, phoneNumber, address, patientInsurances,
					patientAllergies);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;

	}

	private List<PatientAllergy> getPatientAllergies(long id) throws InterruptedException {
		List<PatientAllergy> patientAllergies = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM PatientAllergy WHERE patient_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long allergyId = rs.getLong("id");
				String allergyName = rs.getString("allergy_name");
				String allergyDetails = rs.getString("allergy_details");
				patientAllergies.add(new PatientAllergy(allergyId, allergyName, allergyDetails, id));
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
		return patientAllergies;
	}

	private List<PatientInsurance> getPatientInsurances(long id) throws InterruptedException {
		List<PatientInsurance> patientInsurances = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM PatientInsurance WHERE patient_id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long insuranceId = rs.getLong("id");
				String policyNumber = rs.getString("policy_number");
				String insuranceProvider = rs.getString("insurance_provider");
				String coverageDetails = rs.getString("coverage_details");
				String providerPhone = rs.getString("provider_phone");
				patientInsurances.add(new PatientInsurance(insuranceId, policyNumber, insuranceProvider,
						coverageDetails, providerPhone, id));
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
		return patientInsurances;
	}

}
