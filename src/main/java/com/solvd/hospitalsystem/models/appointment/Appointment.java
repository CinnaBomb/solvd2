package com.solvd.hospitalsystem.models.appointment;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.solvd.hospitalsystem.DateAdapter;
import com.solvd.hospitalsystem.TimestampAdapter;
import com.solvd.hospitalsystem.ListAdapter;
import com.solvd.hospitalsystem.ListSerializer;
import com.solvd.hospitalsystem.ListDeserializer;
import com.solvd.hospitalsystem.TimestampSerializer;
import com.solvd.hospitalsystem.DateSerializer;
import com.solvd.hospitalsystem.DateDeserializer;
import com.solvd.hospitalsystem.TimestampDeserializer;
import com.solvd.hospitalsystem.models.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonRootName("Appointment")
@XmlRootElement(name = "Appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment extends Model {

	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("appointmentDate")
	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement
	private Date appointmentDate;

	@JsonSerialize(using = TimestampSerializer.class)
	@JsonDeserialize(using = TimestampDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("appointmentTime")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	@XmlElement
	private Timestamp appointmentTime;

	@JsonProperty("treatmentNotes")
	@XmlElement
	private String treatmentNotes;

	@JsonProperty("roomId")
	@XmlElement
	private long roomId;
	
	@JsonProperty("employeeId")
	@XmlElement
	private long employeeId;

	@JsonProperty("patientId")
	@XmlElement
	private long patientId;
	
	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("appointmentSymptoms")
	@XmlJavaTypeAdapter(ListAdapter.class)
	@XmlElement
	private List<AppointmentSymptom> appointmentSymptoms;

	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("appointmentDiagnoses")
	@XmlJavaTypeAdapter(ListAdapter.class)
	@XmlElement
	private List<AppointmentDiagnosis> appointmentDiagnoses;

	@JsonSerialize(using = ListSerializer.class)
	@JsonDeserialize(using = ListDeserializer.class)
	@JsonProperty("appointmentMedicines")
	@XmlJavaTypeAdapter(ListAdapter.class)
	@XmlElement
	private List<AppointmentMedicine> appointmentMedicines;

	public Appointment() {
		super();
	}
	
	public Appointment(long id, Date appointmentDate, Timestamp appointmentTime, String treatmentNotes, long roomId,
			long employeeId, long patientId) {
		super(id);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.treatmentNotes = treatmentNotes;
		this.roomId = roomId;
		this.employeeId = employeeId;
		this.patientId = patientId;
	}

	public Appointment(long id, Date appointmentDate, Timestamp appointmentTime, String treatmentNotes, long roomId,
			long employeeId, long patientId, List<AppointmentSymptom> appointmentSymptoms,
			List<AppointmentDiagnosis> appointmentDiagnoses, List<AppointmentMedicine> appointmentMedicines) {
		super(id);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.treatmentNotes = treatmentNotes;
		this.roomId = roomId;
		this.employeeId = employeeId;
		this.patientId = patientId;
		this.appointmentSymptoms = appointmentSymptoms;
		this.appointmentDiagnoses = appointmentDiagnoses;
		this.appointmentMedicines = appointmentMedicines;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Timestamp getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Timestamp appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getTreatmentNotes() {
		return treatmentNotes;
	}

	public void setTreatmentNotes(String treatmentNotes) {
		this.treatmentNotes = treatmentNotes;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public List<AppointmentSymptom> getAppointmentSymptoms() {
		return appointmentSymptoms;
	}

	public void setAppointmentSymptoms(List<AppointmentSymptom> appointmentSymptoms) {
		this.appointmentSymptoms = appointmentSymptoms;
	}

	public List<AppointmentDiagnosis> getAppointmentDiagnoses() {
		return appointmentDiagnoses;
	}

	public void setAppointmentDiagnoses(List<AppointmentDiagnosis> appointmentDiagnoses) {
		this.appointmentDiagnoses = appointmentDiagnoses;
	}

	public List<AppointmentMedicine> getAppointmentMedicines() {
		return appointmentMedicines;
	}

	public void setAppointmentMedicines(List<AppointmentMedicine> appointmentMedicines) {
		this.appointmentMedicines = appointmentMedicines;
	}

}
