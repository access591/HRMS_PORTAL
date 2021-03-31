package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Insurance;
import com.hrms.repository.InsuranceDao;

@Service
public class InsuranceServiceImpl implements InsuranceService{
@Autowired
InsuranceDao insuranceDao;
	@Override
	public void addInsurance(Insurance insurance) {
		insurance.setInsCode(insuranceDao.getMaxId("INS"));
		this.insuranceDao.saveOrUpdate(insurance);
		
	}

	@Override
	public Insurance findInsuranceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInsurance(Insurance d) {
	
		
	}

	@Override
	public void removeInsurance(String id) {
	
		
	}

}
