package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Employee;

public interface EmployeeDao extends GenericDao<Employee> {

	public List<Employee> getEmployeeByDeptCode(String deptCode);
	public List<Employee> getEmployeeByCategoryCode(String categoryCode);
	//public Employee getOneEmployeeByCategoryAndName(String categoryName ,String )
	public List<Employee> findByDateOfJoiningMonth(int month);
}
