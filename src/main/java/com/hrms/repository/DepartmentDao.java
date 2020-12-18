package com.hrms.repository;

import java.util.List;

import com.hrms.model.Department;


public interface DepartmentDao
{
	List<Department>getAllDepartments();
	void addDepartment(Department department);
	
}
