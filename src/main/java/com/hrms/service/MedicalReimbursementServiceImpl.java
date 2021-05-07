package com.hrms.service;


import java.util.List;

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
	
	m2.setSlipNo(medicalReimbursementDao.getMaxId("SLP"));
	this.medicalReimbursementDao.saveOrUpdate(m2);
	return true;
}

@Override
public List<MedicalReimbursement> getAllMedicalReimbursement() {
	List<MedicalReimbursement> listMedicalReimbursement = medicalReimbursementDao.findAll();
	return listMedicalReimbursement;
}

@Override
public MedicalReimbursement findByIdMedicalReimbursementMaster(String id) {
	System.out.println("id>>>>>>>>>>>>>>>>>>>>>>>>>>"+id);
	return this.medicalReimbursementDao.findById(id);
	
}
}
