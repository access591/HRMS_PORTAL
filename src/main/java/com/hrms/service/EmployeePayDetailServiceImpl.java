package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeePayDetail;
import com.hrms.repository.EmployeePayDetailDao;

@Service
public class EmployeePayDetailServiceImpl implements EmployeePayDetailService {

	@Autowired
	EmployeePayDetailDao empPayDetailDao;

	@Override
	public void addEmployeePayDetail(EmployeePayDetail employeePayDetail) {
		this.empPayDetailDao.saveOrUpdate(employeePayDetail);

	}

	@Override
	public List<EmployeePayDetail> getAllEmployeePayDetail() {
		List<EmployeePayDetail> listEmployeePayDetail = empPayDetailDao.findAll();
		return listEmployeePayDetail;
	}

	@Override
	public EmployeePayDetail findEmployeePayDetaildById(String empCode) {
		return empPayDetailDao.findById(empCode);
	}

	@Override
	public void updateEmployeePayDetail(EmployeePayDetail c) {
		this.empPayDetailDao.saveOrUpdate(c);

	}

	@Override
	public void removeEmployeePayDetail(long id) {
		this.empPayDetailDao.delete(id);

	}

	@Override
	public boolean isEmployeePayExists(String empCode) {
		
		return this.empPayDetailDao.isEmployeePayExists(empCode);
	}

}
