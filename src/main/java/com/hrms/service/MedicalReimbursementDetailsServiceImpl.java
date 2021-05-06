package com.hrms.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.repository.MedicalReimbursementDetailsDao;

@Service
public class MedicalReimbursementDetailsServiceImpl  implements MedicalReimbursementDetailsService{
	@Autowired
	 MedicalReimbursementDetailsDao medicalReimbursementDetailsDao;
	@Autowired
	SessionFactory session;
	@Override
	public boolean addMedicalReimbursementDetails(MedicalReimbursementDetail m4) {
	Session s=session.openSession();
	s.beginTransaction();
		m4.setInsDate(new Date());
		m4.setSrNo(medicalReimbursementDetailsDao.getMaxId("MSR"));
		//this.medicalReimbursementDetailsDao.saveOrUpdate(m4);
		s.save(m4);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
