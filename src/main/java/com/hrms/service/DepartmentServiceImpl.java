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
		department.setInsDate(new Date());
		department.setDepartmentCode( departmentDao.getMaxId("DEP"));
		this.departmentDao.saveOrUpdate(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> listDepartment = departmentDao.findAll();
		return listDepartment;
	}

	@Override
	public Department findDepartmentById(String id) {
		System.out.println("department id os : " +id);
		return this.departmentDao.findById(id);
	}

	@Override
	public void updateDepartment(Department d) {
		d.setDeptName(d.getDeptName());
		d.setUpdDate(d.getUpdDate());
		d.setActive(d.getActive());
		this.departmentDao.saveOrUpdate(d);

	}

	@Override
	public void removeDepartment(String id) {
		this.departmentDao.delete(id);

	}

	@Override
	public boolean checkDepartmentExists(Department department) {
		Department e = departmentDao.checkDepartmentExists(department);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}
