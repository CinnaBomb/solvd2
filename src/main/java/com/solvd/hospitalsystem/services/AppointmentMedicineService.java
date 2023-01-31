package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.AppointmentMedicineDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;

public class AppointmentMedicineService {
	private final AppointmentMedicineDAO appointmentMedicineDAO;

	public AppointmentMedicineService() {
		this.appointmentMedicineDAO = new AppointmentMedicineDAO();
	}

	public List<AppointmentMedicine> getAllAppointmentMedicines() throws InterruptedException {
		return this.appointmentMedicineDAO.getAllAppointmentMedicines();
	}

	public List<AppointmentMedicine> getAppointmentMedicinesByParameter(String parameter, Object value)
			throws InterruptedException {
		return this.appointmentMedicineDAO.getAppointmentMedicinesByParameter(parameter, value);
	}

	public AppointmentMedicine getAppointmentMedicineById(long id) throws InterruptedException {
		return this.appointmentMedicineDAO.getEntityById(id);
	}

	public void updateAppointmentMedicine(AppointmentMedicine appointmentMedicine) throws InterruptedException {
		this.appointmentMedicineDAO.updateEntity(appointmentMedicine);
	}

	public AppointmentMedicine createAppointmentMedicine(AppointmentMedicine appointmentMedicine)
			throws InterruptedException {
		return this.appointmentMedicineDAO.createEntity(appointmentMedicine);
	}

	public void deleteAppointmentMedicine(long id) throws InterruptedException {
		this.appointmentMedicineDAO.removeEntity(id);
	}
}
