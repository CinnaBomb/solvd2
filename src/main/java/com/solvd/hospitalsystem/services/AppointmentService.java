package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IAppointmentDAO;
import com.solvd.hospitalsystem.dao.mysql.AppointmentDAO;
import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class AppointmentService {
    private IAppointmentDAO appointmentDAO;

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
    	Appointment appointment = this.appointmentDAO.getEntityById(id);
    	AppointmentSymptomService appointmentSymptomService = new AppointmentSymptomService();
    	AppointmentDiagnosisService appointmentDiagnosisService = new AppointmentDiagnosisService();
    	AppointmentMedicineService appointmentMedicineService = new AppointmentMedicineService();
    	
    	List<AppointmentSymptom> appointmentSymptoms = appointmentSymptomService.getAppointmentSymptomsByAppointmentId(id);
    	List<AppointmentDiagnosis> appointmentDiagnoses = appointmentDiagnosisService.getAppointmentDiagnosesByAppointmentId(id);
    	List<AppointmentMedicine> appointmentMedicines = appointmentMedicineService.getAppointmentMedicineByAppointmentId(id);
    	
    	return new Appointment(appointment.getId(), appointment.getAppointmentDate(), appointment.getAppointmentTime(), 
    		appointment.getTreatmentNotes(), appointment.getRoomId(), appointment.getEmployeeId(), appointment.getPatientId(), 
    		appointmentSymptoms, appointmentDiagnoses, appointmentMedicines);
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
    
	public List<Appointment> getAppointmentsByPatientId(long patientId) throws InterruptedException {
		return appointmentDAO.getAppointmentsByParameter("patient_id", patientId);
	}
	
	public List<Appointment> getAppointmentsByEmployeeId(long employeeId) throws InterruptedException {
		return appointmentDAO.getAppointmentsByParameter("employee_id", employeeId);
	}

	public IAppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(IAppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}


	
	
}
