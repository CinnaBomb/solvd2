package com.solvd.hospitalsystem.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.solvd.hospitalsystem.dao.IAppointmentDAO;
import com.solvd.hospitalsystem.models.appointment.Appointment;

public class AppointmentDAO implements IAppointmentDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Appointment getEntityById(long id) throws InterruptedException {
		return sqlSession.selectOne("Appointment.getAppointmentById", id);
	}

	@Override
	public void updateEntity(Appointment appointment) throws InterruptedException {
		sqlSession.update("Appointment.updateAppointment", appointment);
	}

	@Override
	public Appointment createEntity(Appointment appointment) throws InterruptedException {
		sqlSession.insert("Appointment.insertAppointment", appointment);
		return appointment;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		sqlSession.delete("Appointment.deleteAppointment", id);
	}

	@Override
	public List<Appointment> getAllAppointments() throws InterruptedException {
		return sqlSession.selectList("Appointment.getAllAppointments");
	}

	@Override
	public List<Appointment> getAppointmentsByParameter(String parameter, Object value) throws InterruptedException {
		return sqlSession.selectList("Appointment.getAppointmentsByParameter", value);
	}
}
