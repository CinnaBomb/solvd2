package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public interface IAppointmentSymptomDAO extends IBaseDAO<AppointmentSymptom> {
	List<AppointmentSymptom> getAllAppointmentSymptoms() throws InterruptedException;
	List<AppointmentSymptom> getAppointmentSymptomsByParameter(String parameter, Object value) throws InterruptedException;
}