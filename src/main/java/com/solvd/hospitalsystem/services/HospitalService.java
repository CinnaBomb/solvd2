package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IHospitalDAO;
import com.solvd.hospitalsystem.dao.mysql.HospitalDAO;
import com.solvd.hospitalsystem.models.Hospital;

public class HospitalService {

	private IHospitalDAO hospitalDAO;

	public HospitalService() {
		this.hospitalDAO = new HospitalDAO();
	}

	public List<Hospital> getAllHospitals() throws InterruptedException {
		return hospitalDAO.getAllHospitals();
	}

	public List<Hospital> getHospitalsByParameter(String parameter, Object value) throws InterruptedException {
		return hospitalDAO.getHospitalsByParameter(parameter, value);
	}

	public Hospital createHospital(Hospital hospital) throws InterruptedException {
		return hospitalDAO.createEntity(hospital);
	}

	public void updateHospital(Hospital hospital) throws InterruptedException {
		hospitalDAO.updateEntity(hospital);
	}

	public void removeHospital(long id) throws InterruptedException {
		hospitalDAO.removeEntity(id);
	}

	public Hospital getHospitalById(long id) throws InterruptedException {
		return hospitalDAO.getEntityById(id);
	}
}
