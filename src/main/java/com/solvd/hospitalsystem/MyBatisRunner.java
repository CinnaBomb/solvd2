package com.solvd.hospitalsystem;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.services.HospitalRunner;

public class MyBatisRunner {
	final static Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "/hospitalsystem/src/main/resources/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			logger.info(e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
