package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.AppointmentSymptomDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class AppointmentSymptomService {
	private AppointmentSymptomDAO appointmentSymptomDAO;

	public AppointmentSymptomService() {
		this.appointmentSymptomDAO = new AppointmentSymptomDAO();

	}

	public List<AppointmentSymptom> getAllAppointmentSymptoms() throws InterruptedException {
		return this.appointmentSymptomDAO.getAllAppointmentSymptoms();
	}

	public List<AppointmentSymptom> getAppointmentSymptomsByParameter(String parameter, Object value)
			throws InterruptedException {
		return this.appointmentSymptomDAO.getAppointmentSymptomsByParameter(parameter, value);
	}

	public AppointmentSymptom getAppointmentSymptomById(long id) throws InterruptedException {
		return this.appointmentSymptomDAO.getEntityById(id);
	}

	public void updateAppointmentSymptom(AppointmentSymptom appointmentSymptom) throws InterruptedException {
		this.appointmentSymptomDAO.updateEntity(appointmentSymptom);
	}

	public AppointmentSymptom createAppointmentSymptom(AppointmentSymptom appointmentSymptom)
			throws InterruptedException {
		return this.appointmentSymptomDAO.createEntity(appointmentSymptom);
	}

	public void deleteAppointmentSymptom(long id) throws InterruptedException {
		this.appointmentSymptomDAO.removeEntity(id);
	}

	public List<AppointmentSymptom> getAppointmentSymptomsByAppointmentId(long appointmentId)
			throws InterruptedException {
		return appointmentSymptomDAO.getAppointmentSymptomsByParameter("appointment_id", appointmentId);
	}
}
