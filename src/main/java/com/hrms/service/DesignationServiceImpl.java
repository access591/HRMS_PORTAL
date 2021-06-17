package com.hrms.service;
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
		designation.setInsDate(new Date());
		designation.setDesgCode(designationDao.getMaxId("DES"));
		this.designationDao.saveOrUpdate(designation);
	}

	@Override
	public List<Designation> getAllDesignations() {
		return designationDao.findAll();
		 
	}

	@Override
	public Designation findDesignationById(String id) {
		return designationDao.findById(id);
	}

	@Override
	public void updateDesignation(Designation d) {
		d.setDesgName(d.getDesgName());
		d.setUpdDate(d.getUpdDate());
		d.setActive(d.getActive());
		this.designationDao.saveOrUpdate(d);

	}

	@Override
	public void removeDesignation(String id) {
		this.designationDao.delete(id);

	}

	@Override
	public boolean checkDesignationExists(Designation designation) {
		Designation e = designationDao.checkDesignationExists(designation);
		if (e != null) {
			return true;
		} else {
			return false;
		}
		
	}

}
