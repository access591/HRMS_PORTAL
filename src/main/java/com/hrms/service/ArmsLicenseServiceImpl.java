package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicenseDetails;
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

}
