package com.solvd.hospitalsystem.services;

import com.solvd.hospitalsystem.dao.IBaseDAO;
import com.solvd.hospitalsystem.dao.mysql.AbstractMySQLDAO;
import java.sql.SQLException;


public class DAOFactory {
    private static DAOFactory instance;
    private String url;
    private String username;
    private String password;
    
    private DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance(String url, String username, String password) {
        if (instance == null) {
            instance = new DAOFactory(url, username, password);
        }
        return instance;
    }

    public IBaseDAO<Object> getDAO(String dbType) throws SQLException {
        switch (dbType) {
            case "MySQL":
                return new AbstractMySQLDAO<Object>(url,username,password) {};
            default:
                throw new IllegalArgumentException("Invalid Database Type - not implemented");
        }
    }

}

