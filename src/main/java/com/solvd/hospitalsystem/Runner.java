package com.solvd.hospitalsystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IBaseDAO;
import com.solvd.hospitalsystem.services.DAOFactory;

public class Runner {
	
	final static Logger logger = LogManager.getLogger(Runner.class.getName());

	public static void main(String[] args) throws Exception {

		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("C:\\Users\\House Games\\eclipse-workspace\\hospitalsystem\\src\\main\\resources\\properties\\config.properties")) {
			prop.load(input);
		} catch (IOException e) {
			logger.error(e);
		}
		
		String url = prop.getProperty("db.url");
		String username = prop.getProperty("db.username");
		String password = prop.getProperty("db.password");

		try {
            DAOFactory daoFactory = DAOFactory.getInstance(url,username,password);
            IBaseDAO<Object> objectDAO = daoFactory.getDAO("MySQL");
            
		} catch (SQLException e) {
            logger.error(e);
        }
    }
		
}
