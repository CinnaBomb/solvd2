package com.solvd.hospitalsystem.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.services.HospitalService;

public class ConnectionPoolA {
	final Logger logger = LogManager.getLogger(HospitalService.class.getName());

	private static ConnectionPoolA instance;
	private static final int MAX_CONNECTIONS = 10;
	private LinkedBlockingQueue<Connection> availableConnections;
	private String url;
	private String username;
	private String password;

	public ConnectionPoolA() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					"C:\\Users\\House Games\\eclipse-workspace\\hospitalsystem\\src\\main\\resources\\properties\\config.properties"));
		} catch (FileNotFoundException e) {
			logger.info(e);
		} catch (IOException e) {
			logger.info(e);
		}
		this.url = prop.getProperty("db.url");
		this.username = prop.getProperty("db.username");
		this.password = prop.getProperty("db.password");
		availableConnections = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			try {
				availableConnections.add((Connection) DriverManager.getConnection(url, username, password));
			} catch (SQLException e) {
				logger.info(e);
			}
		}
	}

	public static ConnectionPoolA getInstance() {
		if (instance == null) {
			instance = new ConnectionPoolA();
		}
		return instance;
	}

	public Connection getConnection() throws InterruptedException {
		return availableConnections.take();
	}

	public void releaseConnection(Connection connection) throws InterruptedException {
		availableConnections.put(connection);
	}

}
