package com.solvd.hospitalsystem.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IEmployeeDAO;
import com.solvd.hospitalsystem.models.employee.Employee;
import com.solvd.hospitalsystem.services.HospitalService;
import com.solvd.hospitalsystem.utils.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class EmployeeDAO extends MySQLDAO<Employee> implements IEmployeeDAO {
	final Logger logger = LogManager.getLogger(HospitalService.class.getName());

	private final ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Employee getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE id = ?", 0);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Employee employee = resultSetToEmployee(rs);
				return employee;
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void updateEntity(Employee entity) throws InterruptedException {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE Employee SET first_name = ?, last_name = ?, phone_number = ?, role = ?, hospital_id = ? WHERE id = ?");
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getPhoneNumber());
			statement.setString(4, entity.getRole());
			statement.setLong(5, entity.getHospitalId());
			statement.setLong(6, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public Employee createEntity(Employee entity) throws InterruptedException {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Employee(first_name, last_name, phone_number, role, hospital_id) VALUES(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getPhoneNumber());
			statement.setString(4, entity.getRole());
			statement.setLong(5, entity.getHospitalId());
			statement.executeUpdate();

			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				entity.setId(id);
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			if (connection != null) {
				pool.releaseConnection(connection);
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e);
				}
			}
		}
		return entity;
	}

    @Override
    public void removeEntity(long id) throws InterruptedException {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
        } finally {
            if (connection != null) {
                pool.releaseConnection(connection);
            }
        }
    }


    @Override
    public List<Employee> getAllEmployees() throws InterruptedException {
        Connection connection = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<>();
        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee");
            rs = statement.executeQuery();
            while (rs.next()) {
                Employee emp = resultSetToEmployee(rs);
                employees.add(emp);
            }
            rs.close();
            
        } catch (SQLException e) {
            logger.info(e);
        } finally {
            if (connection != null) {
                pool.releaseConnection(connection);
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.info(e);
                }
            }
        }
        return employees;
    }


    @Override
    public List<Employee> getEmployeeByParameter(String parameter, Object value) throws InterruptedException {
        Connection connection = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE " + parameter + " = ?");
            statement.setObject(1, value);
            rs = statement.executeQuery();
            while (rs.next()) {
                Employee emp = resultSetToEmployee(rs);
                employees.add(emp);
            }
            rs.close();
        } catch (SQLException e) {
            logger.info(e);
        } finally {
            if (connection != null) {
                pool.releaseConnection(connection);
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.info(e);
                }
            }
        }
        return employees;
    }

    private Employee resultSetToEmployee(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phoneNumber = rs.getString("phone_number");
        String role = rs.getString("role");
        long hospitalId = rs.getLong("hospital_id");
        return new Employee(id, firstName, lastName, phoneNumber, role, hospitalId);
    }


}
