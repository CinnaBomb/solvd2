package com.solvd.hospitalsystem.dao.mysql;

import com.solvd.hospitalsystem.Runner;
import com.solvd.hospitalsystem.dao.IBaseDAO;
import com.solvd.hospitalsystem.employee.Employee;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractMySQLDAO<T> implements IBaseDAO<T> {
	
	final Logger logger = LogManager.getLogger(Runner.class.getName());
    private Connection connection;

    public AbstractMySQLDAO(String url, String username, String password) throws SQLException {
        this.setConnection(DriverManager.getConnection(url, username, password));
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                logger.info("Connection established: " + rs.getString(1));
            }
        } catch (SQLException e) {
            logger.info("Connection failed: " + e.getMessage());
        }
    }

    @Override
    public T getEntityById(long id) throws SQLException, InterruptedException {
		return null;
    }

    @Override
    public void updateEntity(T entity) {
    }

    @Override
    public T createEntity(T entity) {
		return entity;
    }

    @Override
    public void removeEntity(long id) {
    }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}


