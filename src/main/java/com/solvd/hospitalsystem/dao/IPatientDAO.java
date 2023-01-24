package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.patient.Patient;

public interface IPatientDAO extends IBaseDAO<Patient>{
	List<Patient> getAllPatients() throws InterruptedException;
	List<Patient> getPatientByParameter(String parameter, Object value) throws InterruptedException;
}
