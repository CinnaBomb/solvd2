package com.solvd.hospitalsystem.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IAppointmentDAO;
import com.solvd.hospitalsystem.dao.IAppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.dao.IAppointmentSymptomDAO;
import com.solvd.hospitalsystem.dao.IEmployeeDAO;
import com.solvd.hospitalsystem.dao.IHospitalDAO;
import com.solvd.hospitalsystem.dao.IPatientDAO;
import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.dao.mysql.AppointmentDAO;
import com.solvd.hospitalsystem.dao.mysql.AppointmentDiagnosisDAO;
import com.solvd.hospitalsystem.dao.mysql.AppointmentSymptomDAO;
import com.solvd.hospitalsystem.dao.mysql.EmployeeDAO;
import com.solvd.hospitalsystem.dao.mysql.HospitalDAO;
import com.solvd.hospitalsystem.dao.mysql.PatientDAO;
import com.solvd.hospitalsystem.dao.mysql.RoomDAO;
import com.solvd.hospitalsystem.models.Hospital;
import com.solvd.hospitalsystem.models.Room;
import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class HospitalService {

	final static Logger logger = LogManager.getLogger(HospitalService.class.getName());

	private static IHospitalDAO hospitalDAO = new HospitalDAO();
	private static IRoomDAO roomDAO = new RoomDAO();
	private static IAppointmentDAO appointmentDAO = new AppointmentDAO();
	private static IAppointmentSymptomDAO appointmentSymptomDAO = new AppointmentSymptomDAO();
	private static IAppointmentDiagnosisDAO appointmentDiagnosisDAO = new AppointmentDiagnosisDAO();
	private static IEmployeeDAO employeeDAO = new EmployeeDAO();
	private static IPatientDAO patientDAO = new PatientDAO();
	
	public static void main(String[] args) throws InterruptedException {
		Hospital hospital = getHospitalById(2);
		List<Room> rooms = getRoomsByHospitalId(2);
		List<Appointment> patientAppointments = getAppointmentsByPatientId(3);
		List<Appointment> employeeAppointments = getAppointmentsByEmployeeId(2);
		List<AppointmentSymptom> appointmentSymptoms = getAppointmentSymptomsByAppointmentId(2);
		List<AppointmentDiagnosis> appointmentDiagnoses = getAppointmentDiagnosesByAppointmentId(2);

		
	}

	public static Hospital getHospitalById(long id) throws InterruptedException {
		Hospital result = hospitalDAO.getEntityById(id);
		logger.info(result.toString());
		return result;
	}

	public static List<Room> getRoomsByHospitalId(long hospitalId) throws InterruptedException {
		List<Room> result = roomDAO.getRoomsByByParameter("hospitalId", hospitalId);
		logger.info(result);
		return result;
	}
	
	public static List<Appointment> getAppointmentsByPatientId(long patientId) throws InterruptedException {
		List<Appointment> result = appointmentDAO.getAppointmentsByParameter("patientId", patientId);
		logger.info(result);
		return result;
	}
	public static List<Appointment> getAppointmentsByEmployeeId(long employeeId) throws InterruptedException {
		List<Appointment> result = appointmentDAO.getAppointmentsByParameter("employeeId", employeeId);
		logger.info(result);
		return result;
	}
	
	public static List<AppointmentSymptom> getAppointmentSymptomsByAppointmentId(long appointmentId) throws InterruptedException {
		List<AppointmentSymptom> result = appointmentSymptomDAO.getAppointmentSymptomsByParameter("appointmentId", appointmentId);
		logger.info(result);
		return result;
	}
	
	public static List<AppointmentDiagnosis> getAppointmentDiagnosesByAppointmentId(long appointmentId) throws InterruptedException {
		List<AppointmentDiagnosis> result = appointmentDiagnosisDAO.getAppointmentDiagnosesByParameter("appointmentId", appointmentId);
		logger.info(result);
		return result;
	}
}
