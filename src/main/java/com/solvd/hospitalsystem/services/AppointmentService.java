package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.AppointmentDAO;
import com.solvd.hospitalsystem.models.appointment.Appointment;

public class AppointmentService {
    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    public List<Appointment> getAllAppointments() throws InterruptedException {
        return this.appointmentDAO.getAllAppointments();
    }

    public List<Appointment> getAppointmentsByParameter(String parameter, Object value) throws InterruptedException {
        return this.appointmentDAO.getAppointmentsByParameter(parameter, value);
    }

    public Appointment getAppointmentById(long id) throws InterruptedException {
        return this.appointmentDAO.getEntityById(id);
    }

    public void updateAppointment(Appointment appointment) throws InterruptedException {
        this.appointmentDAO.updateEntity(appointment);
    }

    public Appointment createAppointment(Appointment appointment) throws InterruptedException {
        return this.appointmentDAO.createEntity(appointment);
    }

    public void deleteAppointment(long id) throws InterruptedException {
        this.appointmentDAO.removeEntity(id);
    }
}
