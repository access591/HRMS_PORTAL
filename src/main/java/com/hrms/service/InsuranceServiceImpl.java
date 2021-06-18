package com.hrms.service;

import java.util.List;

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
	
		return insuranceDao.findById(id);
	}

	@Override
	public void updateInsurance(Insurance ins) {
		ins.setName(ins.getName());
		ins.setAdd1(ins.getAdd1());
		ins.setAdd2(ins.getAdd2());
		ins.setCityCode(ins.getCityCode());
		ins.setCityDesc(ins.getCityDesc());
		ins.setEmail(ins.getEmail());
		ins.setFax(ins.getFax());
		ins.setPhoneNo(ins.getPhoneNo());
		ins.setUpdDate(ins.getUpdDate());
		this.insuranceDao.saveOrUpdate(ins);
	}

	@Override
	public void removeInsurance(String id) {
		this.insuranceDao.delete(id);
		
	}

	@Override
	public List<Insurance> getAllInsurances() {
		
		return insuranceDao.findAll();
		 
	}

}
