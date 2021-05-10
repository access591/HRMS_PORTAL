package com.hrms.service;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MedicalReimbursement;
import com.hrms.repository.MedicalReimbursementDao;

@Service
public class MedicalReimbursementServiceImpl implements MedicalReimbursementService  {
@Autowired
MedicalReimbursementDao medicalReimbursementDao;
@Autowired
SessionFactory sessionfactory;
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

@Override
public void removeMedicalReimbursement(String id) {
	
	Session session = sessionfactory.openSession();
	Object o = session.get(MedicalReimbursement.class, id);
	MedicalReimbursement e = (MedicalReimbursement) o;
	Transaction tx = session.beginTransaction();
	session.delete(e);
	tx.commit();
	session.close();
	

	
}
}
