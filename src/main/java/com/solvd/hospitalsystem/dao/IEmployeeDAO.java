package com.solvd.hospitalsystem.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.employee.Employee;

public interface IEmployeeDAO extends IBaseDAO<Employee>{
    List<Employee> getAllEmployees() throws InterruptedException;
    List<Employee> getEmployeeByParameter(String parameter, Object value) throws InterruptedException;
}