package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.appointment.Appointment;

public interface IAppointmentDAO extends IBaseDAO<Appointment>{
	
    List<Appointment> getAllAppointments() throws InterruptedException;
    List<Appointment> getAppointmentsByParameter(String parameter, Object value) throws InterruptedException;
}
