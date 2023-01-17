package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface ISymptomDAO<T> extends IBaseDAO<T>{
    List<T> getAllSymptoms() throws SQLException;
}