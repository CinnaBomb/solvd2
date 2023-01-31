package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.AppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;

public class AppointmentDiagnosisService {
	private final AppointmentDiagnosisDAO appointmentMedicineDAO;

	public AppointmentDiagnosisService() {
		this.appointmentMedicineDAO = new AppointmentDiagnosisDAO();
	}

	public List<AppointmentDiagnosis> getAllAppointmentDiagnoses() throws InterruptedException {
		return this.appointmentMedicineDAO.getAllAppointmentDiagnoses();
	}

	public List<AppointmentDiagnosis> getAppointmentDiagnosesByParameter(String parameter, Object value)
			throws InterruptedException {
		return this.appointmentMedicineDAO.getAppointmentDiagnosesByParameter(parameter, value);
	}

	public AppointmentDiagnosis getAppointmentDiagnosisById(long id) throws InterruptedException {
		return this.appointmentMedicineDAO.getEntityById(id);
	}

	public void updateAppointmentDiagnosis(AppointmentDiagnosis appointmentMedicine) throws InterruptedException {
		this.appointmentMedicineDAO.updateEntity(appointmentMedicine);
	}

	public AppointmentDiagnosis createAppointmentDiagnosis(AppointmentDiagnosis appointmentMedicine)
			throws InterruptedException {
		return this.appointmentMedicineDAO.createEntity(appointmentMedicine);
	}

	public void deleteAppointmentDiagnosis(long id) throws InterruptedException {
		this.appointmentMedicineDAO.removeEntity(id);
	}
}
