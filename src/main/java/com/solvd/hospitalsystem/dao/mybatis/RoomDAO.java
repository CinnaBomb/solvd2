package com.solvd.hospitalsystem.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.models.Room;

public class RoomDAO implements IRoomDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Room getEntityById(long id) throws InterruptedException {
		return sqlSession.selectOne("Room.getRoomById", id);
	}

	@Override
	public void updateEntity(Room room) throws InterruptedException {
		sqlSession.update("Room.updateRoom", room);
	}

	@Override
	public Room createEntity(Room room) throws InterruptedException {
		sqlSession.insert("Room.insertRoom", room);
		return room;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		sqlSession.delete("Room.deleteRoom", id);
	}

	@Override
	public List<Room> getAllRooms() throws InterruptedException {
		return sqlSession.selectList("Room.getAllRooms");
	}

	@Override
	public List<Room> getRoomsByByParameter(String parameter, Object value) throws InterruptedException {
		return sqlSession.selectList("Room.getRoomsByByParameter", value);
	}
}
