package com.solvd.hospitalsystem;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class StAXParsingExample {
	final static Logger logger = LogManager.getLogger(StAXParsingExample.class.getName());
	private Appointment appointment;	
	private AppointmentSymptom appointmentSymptom;
	private AppointmentDiagnosis appointmentDiagnosis;
	private AppointmentMedicine appointmentMedicine;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	public void parseXML() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("Appointment.xml"));
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {	
				case XMLStreamConstants.START_ELEMENT:
					if (reader.getLocalName().equalsIgnoreCase("appointment")) {
						appointment = new Appointment();
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentSymptoms")) {
						appointment.setAppointmentSymptoms(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentSymptom")) {
						appointmentSymptom = new AppointmentSymptom();
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentDiagnoses")) {
						appointment.setAppointmentDiagnoses(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentDiagnosis")) {
						appointmentDiagnosis = new AppointmentDiagnosis();
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentMedicines")) {
						appointment.setAppointmentMedicines(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentMedicine")) {
						appointmentMedicine = new AppointmentMedicine();
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String data = reader.getText().trim();
					if (appointment != null && appointment.getId() == 0) {
						appointment.setId(Long.parseLong(data));
					} else if (appointmentSymptom != null && appointmentSymptom.getSymptomName() == null) {
						appointmentSymptom.setSymptomName(data);
					} else if (appointmentDiagnosis != null && appointmentDiagnosis.getDiagnosisName() == null) {
						appointmentDiagnosis.setDiagnosisName(data);
					} else if (appointmentMedicine != null && appointmentMedicine.getMedicineName() == null) {
						appointmentMedicine.setMedicineName(data);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if (reader.getLocalName().equalsIgnoreCase("appointment")) {
						// Add appointment object to a list or do any other processing
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentSymptom")) {
						appointment.getAppointmentSymptoms().add(appointmentSymptom);
						appointmentSymptom = null;
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentDiagnosis")) {
						appointment.getAppointmentDiagnoses().add(appointmentDiagnosis);
						appointmentDiagnosis = null;
					} else if (reader.getLocalName().equalsIgnoreCase("appointmentMedicine")) {
						appointment.getAppointmentMedicines().add(appointmentMedicine);
						appointmentMedicine = null;
					}
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
