package com.solvd.hospitalsystem.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.services.HospitalService;


public class Connection {

	final Logger logger = LogManager.getLogger(HospitalService.class.getName());

	private java.sql.Connection connection;

	public Connection() throws InterruptedException{
		this.connection = (java.sql.Connection) ConnectionPoolA.getInstance().getConnection();
	}

	public PreparedStatement prepareStatement(String query){
		try {
			return connection.prepareStatement(query);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;
	}
	
    public ResultSet executeQuery(String query){
        Statement stmt;
		try {
			stmt = connection.createStatement();
	        return stmt.executeQuery(query);

		} catch (SQLException e) {
			logger.info(e);
		}
		return null;

    }

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

    public java.sql.Connection getConnection() {
		return connection;
	}

	public void setConnection(java.sql.Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement prepareStatement(String query, int returnGeneratedKeys){
        try {
			return connection.prepareStatement(query, returnGeneratedKeys);
		} catch (SQLException e) {
			logger.error(e);
		}
		return null;
    }

}



