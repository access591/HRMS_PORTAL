package com.hrms.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Designation;
import com.hrms.repository.DesignationDao;
@Service
public class DesignationServiceImpl implements DesignationService {
	@Autowired
	DesignationDao designationDao;
	@Override
	public void addDesignation(Designation designation) {
		designation.setIns_date(new Date());
		this.designationDao.addDesignation(designation);
		
	}
	@Override
	public List<Designation> getAllDesignations() {
		
		List<Designation>  listDesignation=designationDao.getAllDesignations();
		return listDesignation;
		
	}
	@Override
	public Designation findDesignationById(String id) {
		return designationDao.findDesignationById(id);	
	}

}
