package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAppointmentSymptomDAO<T> extends IBaseDAO<T>{
    List<T> getAllPatientSymptoms() throws SQLException;
}