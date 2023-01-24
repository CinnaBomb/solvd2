package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;

public interface IAppointmentDiagnosisDAO extends IBaseDAO<AppointmentDiagnosis>{
	List<AppointmentDiagnosis> getAllAppointmentDiagnoses() throws InterruptedException;
	List<AppointmentDiagnosis> getAppointmentDiagnosesByParameter(String parameter, Object value) throws InterruptedException;
}
