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
	employee.setEmp_Code(employeeDao.getMAX_Id("EMP"));
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
	e.setEmp_Name(e.getEmp_Name());
	e.setEmp_Img(e.getEmp_Img());
	e.seteActive(e.geteActive());
	e.setComp_Code(e.getComp_Code());
	e.setDate_Of_Birth(e.getDate_Of_Birth());
	e.setBirth_State(e.getBirth_State());
	e.setEmp_Ref_No(e.getEmp_Ref_No());
	e.setEmp_Type(e.getEmp_Type());
	e.setLocation_Type(e.getLocation_Type());
	e.setWeekly_Working_Day(e.getWeekly_Working_Day());
	e.setBirth_Place(e.getBirth_Place());
	e.setDomicile(e.getDomicile());
	//Second Step Start...................................................
	e.setCard_No(e.getCard_No());
	e.setEmp_Designation(e.getEmp_Designation());
	e.setShift_Code(e.getShift_Code());
	
	
	
	
	
	
	
	this.employeeDao.saveOrUpdate(e);
		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

}
