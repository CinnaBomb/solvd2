package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IHospitalDAO<T> extends IBaseDAO<T>{
    List<T> getAllHospitals() throws SQLException;
}

