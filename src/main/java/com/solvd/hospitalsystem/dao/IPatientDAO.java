package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDAO<T> extends IBaseDAO<T>{
	List<T> getAllPatients() throws SQLException;
}
