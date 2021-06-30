package com.hrms.service;

import java.util.List;
import java.util.Map;

import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;

public interface EmployeeService {
	public void addEmployee(Employee employee);
	List<Employee>getAllEmployees();
	Employee findEmployeeById(String id);
	public void updateEmployee(Employee e);
	public void removeEmployeet(String id);
	public List<Employee> getEmployeeByDeptCode(String deptCode);

	public List<EmployeeUtil> getAllEmployeesAndArms();

	public List<Employee> getEmployeeByCategoryCode(String categoryCode);
	public List<Employee> findByDateOfJoiningMonth(int month);
	public List<Employee> findByDepartmentCode(String deptCode);
	public List<Employee> findByempCode(String empCode);
	
	public Map<String, Long> countRecordByCategory();
	
	
	public List<Employee> findAllEmployee();


}
