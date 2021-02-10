package com.hrms.service;

import java.util.List;


import com.hrms.model.Employee;

public interface EmployeeService {
	public void addEmployee(Employee employee);
	List<Employee>getAllEmployees();
	Employee findEmployeeById(String id);
	public void updateEmployee(Employee d);
	public void removeEmployeet(String id);


}
