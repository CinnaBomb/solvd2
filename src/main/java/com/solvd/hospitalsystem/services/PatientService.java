package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IPatientDAO;
import com.solvd.hospitalsystem.dao.mysql.PatientDAO;
import com.solvd.hospitalsystem.models.patient.Patient;

public class PatientService {

	private IPatientDAO patientDAO;

	public PatientService() {
		this.patientDAO = new PatientDAO();
	}

	public List<Patient> getAllPatients() throws InterruptedException {
		return patientDAO.getAllPatients();
	}

	public List<Patient> getPatientByParameter(String parameter, Object value) throws InterruptedException {
		return patientDAO.getPatientByParameter(parameter, value);
	}

	public Patient getPatientById(long id) throws InterruptedException {
		return patientDAO.getEntityById(id);
	}

	public void updatePatient(Patient patient) throws InterruptedException {
		patientDAO.updateEntity(patient);
	}

	public Patient createPatient(Patient patient) throws InterruptedException {
		return patientDAO.createEntity(patient);
	}

	public void deletePatient(long id) throws InterruptedException {
		patientDAO.removeEntity(id);
	}
}
