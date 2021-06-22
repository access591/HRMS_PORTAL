package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.Award;
import com.hrms.model.Employee;
import com.hrms.repository.ArmsLicenseDao;
@Service
public class ArmsLicenseServiceImpl implements ArmsLicenseService {
	@Autowired
	ArmsLicenseDao armsLicenseDao;
	@Override
	public void addArmsLicenseDetails(ArmsLicenseDetails armsLicense) {
		armsLicense.setArmsCode(armsLicenseDao.getMaxId("ARM"));
		
		this.armsLicenseDao.saveOrUpdate(armsLicense);
		
	}
	@Override
	public ArmsLicenseDetails findArmsLicenseById(String id) {
		return armsLicenseDao.findById(id);
	}
	@Override
	public List<ArmsLicenseDetails> getAllArmsLicenses() {
		List<ArmsLicenseDetails> listArmsLicense = armsLicenseDao.findAll();
		return listArmsLicense;
	}
	@Override
	public void updateArmsLicenseService(ArmsLicenseDetails armsLicense) {
		this.armsLicenseDao.saveOrUpdate(armsLicense);
		
	}
	@Override
	public void removeArmsLicenseService(String id2) {
		this.armsLicenseDao.delete(id2);
		
	}
	@Override
	public ArmsLicenseDetails findArmsByEmpEmpCode(String id) {
		try {
			return armsLicenseDao.findArmsByEmpEmpCode(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
