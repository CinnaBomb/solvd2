package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.IEmployeeDAO;
import com.solvd.hospitalsystem.dao.mysql.EmployeeDAO;
import com.solvd.hospitalsystem.models.employee.Employee;

public class EmployeeService {
	private IEmployeeDAO employeeDAO;

	public EmployeeService() {
		this.employeeDAO = new EmployeeDAO();
	}

	public List<Employee> getAllEmployees() throws InterruptedException {
		return this.employeeDAO.getAllEmployees();
	}

	public List<Employee> getEmployeesByParameter(String parameter, Object value) throws InterruptedException {
		return this.employeeDAO.getEmployeesByParameter(parameter, value);
	}

	public Employee getEmployeeById(long id) throws InterruptedException {
		return this.employeeDAO.getEntityById(id);
	}

	public void updateEmployee(Employee employee) throws InterruptedException {
		this.employeeDAO.updateEntity(employee);
	}

	public Employee createEmployee(Employee employee) throws InterruptedException {
		return this.employeeDAO.createEntity(employee);
	}

	public void deleteEmployee(long id) throws InterruptedException {
		this.employeeDAO.removeEntity(id);
	}
}