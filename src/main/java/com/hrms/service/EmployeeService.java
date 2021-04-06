package com.hrms.service;

import java.util.List;


import com.hrms.model.Employee;

public interface EmployeeService {
	public void addEmployee(Employee employee);
	List<Employee>getAllEmployees();
	Employee findEmployeeById(String id);
	public void updateEmployee(Employee e);
	public void removeEmployeet(String id);
	public List<Employee> getEmployeeByDeptCode(String deptCode);


}
