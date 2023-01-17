package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO<T> extends IBaseDAO<T>{
    List<T> getAllEmployees() throws SQLException;
}