package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Employee;
import com.hrms.repository.EmployeeDao;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	@Override
	public void addEmployee(Employee employee) {
	employee.setEmpCode(employeeDao.getMaxId("EMP"));
	this.employeeDao.saveOrUpdate(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> listEmployee = employeeDao.findAll();
		return listEmployee;
	}

	@Override
	public Employee findEmployeeById(String id) {
		
		return employeeDao.findById(id);
	}

	@Override
	public void updateEmployee(Employee e) {

	
	this.employeeDao.saveOrUpdate(e);
		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {
		return this.employeeDao.getEmployeeByDeptCode(deptCode);
	}

	@Override
	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {
		
		return this.employeeDao.getEmployeeByCategoryCode(categoryCode);
	}

	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {
		System.out.println("Service methosd calling");
		return this.employeeDao.findByDateOfJoiningMonth(month);
	}

}
