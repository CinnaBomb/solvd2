package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.AppointmentSymptomDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class AppointmentSymptomService {
	private final AppointmentSymptomDAO appointmentMedicineDAO;

	public AppointmentSymptomService() {
		this.appointmentMedicineDAO = new AppointmentSymptomDAO();
	}

	public List<AppointmentSymptom> getAllAppointmentSymptoms() throws InterruptedException {
		return this.appointmentMedicineDAO.getAllAppointmentSymptoms();
	}

	public List<AppointmentSymptom> getAppointmentSymptomsByParameter(String parameter, Object value)
			throws InterruptedException {
		return this.appointmentMedicineDAO.getAppointmentSymptomsByParameter(parameter, value);
	}

	public AppointmentSymptom getAppointmentSymptomById(long id) throws InterruptedException {
		return this.appointmentMedicineDAO.getEntityById(id);
	}

	public void updateAppointmentSymptom(AppointmentSymptom appointmentMedicine) throws InterruptedException {
		this.appointmentMedicineDAO.updateEntity(appointmentMedicine);
	}

	public AppointmentSymptom createAppointmentSymptom(AppointmentSymptom appointmentMedicine)
			throws InterruptedException {
		return this.appointmentMedicineDAO.createEntity(appointmentMedicine);
	}

	public void deleteAppointmentSymptom(long id) throws InterruptedException {
		this.appointmentMedicineDAO.removeEntity(id);
	}
}
