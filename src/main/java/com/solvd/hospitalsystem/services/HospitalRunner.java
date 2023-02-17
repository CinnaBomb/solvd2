package com.solvd.hospitalsystem.services;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.MyBatisRunner;
import com.solvd.hospitalsystem.dao.mybatis.AppointmentDAO;
import com.solvd.hospitalsystem.services.AppointmentService;
import com.solvd.hospitalsystem.models.appointment.Appointment;

public class HospitalRunner {
    final static Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

    public static void main(String[] args) throws InterruptedException {
        SqlSessionFactory sqlSessionFactory = MyBatisRunner.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            appointmentDAO.setSqlSession(sqlSession);
            AppointmentService appointmentService = new AppointmentService(appointmentDAO);
            Appointment appt = appointmentService.getAppointmentById(2);
            logger.info(appt.getAppointmentDate());
            logger.info(appt.getAppointmentTime());
            logger.info(appt.getRoomId());
            logger.info(appt.getEmployeeId());
        }
    }
}
