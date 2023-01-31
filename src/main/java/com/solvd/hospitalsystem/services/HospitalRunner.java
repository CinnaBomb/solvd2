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

public class HospitalRunner {

	final static Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private static IHospitalDAO hospitalDAO = new HospitalDAO();
	private static IRoomDAO roomDAO = new RoomDAO();
	private static IAppointmentDAO appointmentDAO = new AppointmentDAO();
	private static IAppointmentSymptomDAO appointmentSymptomDAO = new AppointmentSymptomDAO();
	private static IAppointmentDiagnosisDAO appointmentDiagnosisDAO = new AppointmentDiagnosisDAO();
	//private static IEmployeeDAO employeeDAO = new EmployeeDAO();
	//private static IPatientDAO patientDAO = new PatientDAO();

	public static void main(String[] args) throws InterruptedException {
		List<Hospital> hospitals = getHospitalsByName("General Hospital");
		List<Room> rooms = getRoomsByHospitalId(2);
		List<Appointment> patientAppointments = getAppointmentsByPatientId(3);
		List<Appointment> employeeAppointments = getAppointmentsByEmployeeId(2);
		List<AppointmentSymptom> appointmentSymptoms = getAppointmentSymptomsByAppointmentId(2);
		List<AppointmentDiagnosis> appointmentDiagnoses = getAppointmentDiagnosesByAppointmentId(2);
	}

	public static Hospital getHospitalById(long id) throws InterruptedException {
		Hospital result = hospitalDAO.getEntityById(id);
		logger.info("get hospital by id");
		logger.info(result.getAddress());
		return result;
	}

	public static List<Hospital> getHospitalsByName(String name) throws InterruptedException {
		List<Hospital> result = hospitalDAO.getHospitalsByParameter("hospital_name", name);
		logger.info("get hospitals by name");
		for (Hospital hospital : result) {
			logger.info(hospital.getAddress());
		}
		return result;
	}

	public static List<Room> getRoomsByHospitalId(long hospitalId) throws InterruptedException {
		List<Room> result = roomDAO.getRoomsByByParameter("hospital_id", hospitalId);
		logger.info("get rooms by hospital id");
		for (Room room : result) {
			logger.info(room.toString());
		}
		return result;
	}

	public static List<Appointment> getAppointmentsByPatientId(long patientId) throws InterruptedException {
		List<Appointment> result = appointmentDAO.getAppointmentsByParameter("patient_id", patientId);
		logger.info("get appt by patient id");
		for (Appointment appt : result) {
			logger.info(appt.toString());
		}
		return result;
	}

	public static List<Appointment> getAppointmentsByEmployeeId(long employeeId) throws InterruptedException {
		List<Appointment> result = appointmentDAO.getAppointmentsByParameter("employee_id", employeeId);
		logger.info(result);
		return result;
	}

	public static List<AppointmentSymptom> getAppointmentSymptomsByAppointmentId(long appointmentId)
			throws InterruptedException {
		List<AppointmentSymptom> result = appointmentSymptomDAO.getAppointmentSymptomsByParameter("appointment_id",
				appointmentId);
		logger.info(result);
		return result;
	}

	public static List<AppointmentDiagnosis> getAppointmentDiagnosesByAppointmentId(long appointmentId)
			throws InterruptedException {
		List<AppointmentDiagnosis> result = appointmentDiagnosisDAO.getAppointmentDiagnosesByParameter("appointment_id",
				appointmentId);
		logger.info(result);
		return result;
	}
}
