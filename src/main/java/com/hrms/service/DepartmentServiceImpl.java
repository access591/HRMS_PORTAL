package com.hrms.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Department;
import com.hrms.repository.DepartmentDao;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentDao departmentDao;

	@Override
	public void addDepartment(Department department) {
		department.setIns_date(new Date());
		this.departmentDao.addDepartment(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> listDepartment = departmentDao.getAllDepartments();
		return listDepartment;
	}


}
