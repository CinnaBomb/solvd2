package com.solvd.hospitalsystem.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.*;
import com.solvd.hospitalsystem.utils.Connection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.Runner;

public class ConnectionPoolA {
	final static Logger logger = LogManager.getLogger(Runner.class.getName());

	private static ConnectionPoolA instance;
	private static final int MAX_CONNECTIONS = 10;
	private LinkedBlockingQueue<Connection> availableConnections;
	private String url;
	private String username;
	private String password;

	private ConnectionPoolA() throws InterruptedException, SQLException {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("C:\\Users\\House Games\\eclipse-workspace\\hospitalsystem\\src\\main\\resources\\properties\\config.properties"));
			url = prop.getProperty("db.url");
			username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            availableConnections = new LinkedBlockingQueue<Connection>();
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                 Connection connection = instance.getConnection();
                availableConnections.add(connection);
            }
        } catch (IOException e) {
            logger.error(e);
        }
		this.availableConnections = availableConnections;
	}


	public synchronized Connection getConnection() throws InterruptedException{
		Connection connection = availableConnections.take();
		return connection;
	}

	public void close() {
		for (Connection connection : availableConnections) {
			connection.close();
		}
	}
	
    public static ConnectionPoolA getInstance() throws InterruptedException, SQLException {
        if (instance == null) {
            instance = new ConnectionPoolA();
        }
        return instance;
    }

}

