package com.solvd.hospitalsystem;

import java.util.List;
import java.util.stream.Collectors;

import com.solvd.hospitalsystem.models.Hospital;
import com.solvd.hospitalsystem.models.Room;
import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;
import com.solvd.hospitalsystem.models.employee.Employee;
import com.solvd.hospitalsystem.models.patient.Patient;
import com.solvd.hospitalsystem.services.AppointmentDiagnosisService;
import com.solvd.hospitalsystem.services.AppointmentMedicineService;
import com.solvd.hospitalsystem.services.AppointmentService;
import com.solvd.hospitalsystem.services.AppointmentSymptomService;
import com.solvd.hospitalsystem.services.EmployeeService;
import com.solvd.hospitalsystem.services.HospitalService;
import com.solvd.hospitalsystem.services.PatientService;
import com.solvd.hospitalsystem.services.RoomService;

public class HospitalFactory {
	private final HospitalService hospitalService;
	private final RoomService roomService;
	private final EmployeeService employeeService;
	private final AppointmentService appointmentService;
	private final AppointmentSymptomService appointmentSymptomService;
	private final AppointmentDiagnosisService appointmentDiagnosisService;
	private final AppointmentMedicineService appointmentMedicineService;
	private final PatientService patientService;

	public HospitalFactory(HospitalService hospitalService, RoomService roomService, EmployeeService employeeService,
			AppointmentService appointmentService, AppointmentSymptomService appointmentSymptomService,
			AppointmentDiagnosisService appointmentDiagnosisService,
			AppointmentMedicineService appointmentMedicineService, PatientService patientService) {
		this.hospitalService = new HospitalService();
		this.roomService = new RoomService();
		this.employeeService = new EmployeeService();
		this.appointmentService = new AppointmentService();
		this.appointmentSymptomService = new AppointmentSymptomService();
		this.appointmentDiagnosisService = new AppointmentDiagnosisService();
		this.appointmentMedicineService = new AppointmentMedicineService();
		this.patientService = new PatientService();
	}

	public Hospital createHospital(long id) throws InterruptedException {
		Hospital hospital = hospitalService.getHospitalById(id);
		List<Room> rooms = roomService.getRoomsByParameter("hospital_id", id).stream()
				.map(r -> new Room(r.getId(), r.getRoomNumber(), r.getRoomType(), r.getAvailability(), r.getHospitalId()))
                        .collect(Collectors.toList());
		return new Hospital(hospital.getId(), hospital.getHospitalName(), hospital.getAddress(), rooms);
	}
	
	public Appointment createAppointment(long id) throws InterruptedException {
        Appointment appointment = appointmentService.getAppointmentById(id);
        Room room = roomService.getRoomById(appointment.getRoomId());
        Employee employee = employeeService.getEmployeeById(appointment.getEmployeeId());
        Patient patient = patientService.getPatientById(appointment.getPatientId());
        List<AppointmentSymptom> appointmentSymptoms = appointmentSymptomService.getAppointmentSymptomsByParameter("appointment_id", id)
                                      .stream()
                                      .map(as -> new AppointmentSymptom(as.getId(), as.getSymptomName(), as.getSeverity(), as.getAppointmentId()))
                                      .collect(Collectors.toList());
        List<AppointmentDiagnosis> appointmentDiagnoses = appointmentDiagnosisService.getAppointmentDiagnosesByParameter("appointment_id", id)
                                      .stream()
                                      .map(ad -> new AppointmentDiagnosis(ad.getId(), ad.getDiagnosisName(), ad.getDetails(), ad.getAppointmentId()))
                                      .collect(Collectors.toList());
        List<AppointmentMedicine> appointmentMedicines = appointmentMedicineService.getAppointmentMedicinesByParameter("appointment_id", id)
                                      .stream()
                                      .map(am -> new AppointmentMedicine(am.getId(), am.getMedicineName(), am.getAppointmentId()))
                                      .collect(Collectors.toList());
        return new Appointment(appointment.getId(), appointment.getAppointmentDate(), appointment.getAppointmentTime(), appointment.getTreatmentNotes(), 
        		appointment.getRoomId(), appointment.getEmployeeId(), appointment.getPatientId(), appointment.getAppointmentSymptoms(), 
        		appointment.getAppointmentDiagnoses(), appointment.getAppointmentMedicines());
    }
}
