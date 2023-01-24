package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.Hospital;

public interface IHospitalDAO extends IBaseDAO<Hospital>{
    List<Hospital> getAllHospitals() throws InterruptedException;
    List<Hospital> getHospitalByParameter(String parameter, Object value) throws InterruptedException;
}

