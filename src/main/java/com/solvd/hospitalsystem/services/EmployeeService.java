package com.solvd.hospitalsystem.services;

import java.util.List;

import com.solvd.hospitalsystem.dao.mysql.EmployeeDAO;
import com.solvd.hospitalsystem.models.employee.Employee;

public class EmployeeService {
	private EmployeeDAO appointmentMedicineDAO;

	public EmployeeService() {
		this.appointmentMedicineDAO = new EmployeeDAO();
	}

	public List<Employee> getAllEmployees() throws InterruptedException {
		return this.appointmentMedicineDAO.getAllEmployees();
	}

	public List<Employee> getEmployeesByParameter(String parameter, Object value) throws InterruptedException {
		return this.appointmentMedicineDAO.getEmployeesByParameter(parameter, value);
	}

	public Employee getEmployeeById(long id) throws InterruptedException {
		return this.appointmentMedicineDAO.getEntityById(id);
	}

	public void updateEmployee(Employee appointmentMedicine) throws InterruptedException {
		this.appointmentMedicineDAO.updateEntity(appointmentMedicine);
	}

	public Employee createEmployee(Employee appointmentMedicine) throws InterruptedException {
		return this.appointmentMedicineDAO.createEntity(appointmentMedicine);
	}

	public void deleteEmployee(long id) throws InterruptedException {
		this.appointmentMedicineDAO.removeEntity(id);
	}
}