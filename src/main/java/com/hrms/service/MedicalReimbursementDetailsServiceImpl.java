package com.hrms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.repository.MedicalReimbursementDetailsDao;

@Service
public class MedicalReimbursementDetailsServiceImpl  implements MedicalReimbursementDetailsService{
	@Autowired
	 MedicalReimbursementDetailsDao medicalReimbursementDetailsDao;
	@Override
	public boolean addMedicalReimbursementDetails(MedicalReimbursementDetail m4) {
	
		m4.setInsDate(new Date());
		m4.setSrNo(medicalReimbursementDetailsDao.getMaxId("MSR"));
		this.medicalReimbursementDetailsDao.saveOrUpdate(m4);
		
		return true;
	}

}
