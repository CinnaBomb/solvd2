package com.solvd.hospitalsystem.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospitalsystem.Runner;

public class Connection {

	final Logger logger = LogManager.getLogger(Runner.class.getName());

	private java.sql.Connection connection;

	public Connection(String url, String username, String password) throws SQLException {
		this.connection = java.sql.DriverManager.getConnection(url, username, password);
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	public void executeQuery(String query) {
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				logger.info("ID: " + id);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}



