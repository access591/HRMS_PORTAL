package com.hrms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MedicalReimbursement;
import com.hrms.repository.MedicalReimbursementDao;

@Service
public class MedicalReimbursementServiceImpl implements MedicalReimbursementService  {
@Autowired
MedicalReimbursementDao medicalReimbursementDao;

@Override
public boolean addMedicalReimbursement(MedicalReimbursement m2) {
	m2.setInsDate(new Date());
	m2.setSlipNo(medicalReimbursementDao.getMaxId("SLP"));
	this.medicalReimbursementDao.saveOrUpdate(m2);
	return true;
}
}
