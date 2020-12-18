package com.hrms.service;

import java.util.List;

import com.hrms.model.Department;

public interface DepartmentService 
{
	public void addDepartment(Department department);
	List<Department>getAllDepartments();

}
