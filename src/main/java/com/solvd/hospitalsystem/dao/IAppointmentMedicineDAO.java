package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;

public interface IAppointmentMedicineDAO {

	List<AppointmentMedicine> getAllAppointmentMedicines() throws InterruptedException;
	List<AppointmentMedicine> getAppointmentMedicinesByParameter(String parameter, Object value) throws InterruptedException;

}
