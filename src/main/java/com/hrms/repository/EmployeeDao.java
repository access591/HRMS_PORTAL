package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeUtil;

public interface EmployeeDao extends GenericDao<Employee> {

	public List<Employee> getEmployeeByDeptCode(String deptCode);

	public List<EmployeeUtil> getAllEmployeesAndArms();
}
