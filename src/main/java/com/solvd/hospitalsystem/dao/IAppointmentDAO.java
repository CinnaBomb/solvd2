package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAppointmentDAO<T> extends IBaseDAO<T>{
    List<T> getAllAppointments() throws SQLException;
}
