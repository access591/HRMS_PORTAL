package com.hrms.repository;

import java.util.List;

import com.hrms.model.Department;



public interface DepartmentDao
{
	List<Department>getAllDepartments();
	void addDepartment(Department department);
	Department findDepartmentById(String id);
	public void updateDepartment(Department d);
	public void removeDepartment(String id);
	
}
