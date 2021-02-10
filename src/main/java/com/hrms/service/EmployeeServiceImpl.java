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
	public void updateEmployee(Employee d) {
	d.setEmp_Name(d.getEmp_Name());

		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

}
