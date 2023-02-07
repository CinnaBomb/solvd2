package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IAppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.dao.mysql.AppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;

public class AppointmentDiagnosisService {
	private  IAppointmentDiagnosisDAO appointmentDiagnosisDAO;

	public AppointmentDiagnosisService() {
		this.appointmentDiagnosisDAO = new AppointmentDiagnosisDAO();
	}

	public List<AppointmentDiagnosis> getAllAppointmentDiagnoses() throws InterruptedException {
		return this.appointmentDiagnosisDAO.getAllAppointmentDiagnoses();
	}

	public List<AppointmentDiagnosis> getAppointmentDiagnosesByParameter(String parameter, Object value)
			throws InterruptedException {
		return this.appointmentDiagnosisDAO.getAppointmentDiagnosesByParameter(parameter, value);
	}

	public AppointmentDiagnosis getAppointmentDiagnosisById(long id) throws InterruptedException {
		return this.appointmentDiagnosisDAO.getEntityById(id);
	}

	public void updateAppointmentDiagnosis(AppointmentDiagnosis appointmentDiagnosis) throws InterruptedException {
		this.appointmentDiagnosisDAO.updateEntity(appointmentDiagnosis);
	}

	public AppointmentDiagnosis createAppointmentDiagnosis(AppointmentDiagnosis appointmentDiagnosis)
			throws InterruptedException {
		return this.appointmentDiagnosisDAO.createEntity(appointmentDiagnosis);
	}

	public void deleteAppointmentDiagnosis(long id) throws InterruptedException {
		this.appointmentDiagnosisDAO.removeEntity(id);
	}

	public List<AppointmentDiagnosis> getAppointmentDiagnosesByAppointmentId(long appointmentId)
			throws InterruptedException {
		return appointmentDiagnosisDAO.getAppointmentDiagnosesByParameter("appointment_id",
				appointmentId);
	}
}
