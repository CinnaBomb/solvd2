package com.solvd.hospitalsystem.dao;

import java.sql.SQLException;
import java.util.List;

public interface IRoomDAO<T> extends IBaseDAO<T>{
    List<T> getAllRooms() throws SQLException;
}