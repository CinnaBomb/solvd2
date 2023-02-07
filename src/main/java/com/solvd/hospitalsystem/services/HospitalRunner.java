package com.solvd.hospitalsystem.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.models.appointment.Appointment;

public class HospitalRunner {
	
	final static Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	public static void main(String[] args) throws InterruptedException {
		AppointmentService apptService = new AppointmentService();
		Appointment appt = apptService.getAppointmentById(2);
		logger.info(appt.getAppointmentDate());
		logger.info(appt.getAppointmentTime());
		logger.info(appt.getRoomId());
		logger.info(appt.getEmployeeId());

	}










}
