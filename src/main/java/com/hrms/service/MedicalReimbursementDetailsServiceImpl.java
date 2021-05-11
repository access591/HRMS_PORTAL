package com.hrms.service;

import java.util.Date;
import java.util.List;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Loan;
import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.model.Module;
import com.hrms.repository.MedicalReimbursementDetailsDao;

@Service
public class MedicalReimbursementDetailsServiceImpl  implements MedicalReimbursementDetailsService{
	@Autowired
	 MedicalReimbursementDetailsDao medicalReimbursementDetailsDao;
	
	@Autowired
	SessionFactory sessionFactory;

	
	
	@Override
	public boolean addMedicalReimbursementDetails(MedicalReimbursementDetail m4) {
	Session s=sessionFactory.openSession();
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
	@Override
	public List<MedicalReimbursementDetail> getAllMedicalReimbursementDetails() {
		List<MedicalReimbursementDetail> listMedicalReimbursementDetail = medicalReimbursementDetailsDao.findAll();
		return listMedicalReimbursementDetail;
	}
	
	@Override
	public List<MedicalReimbursementDetail> getAllMedicalReimbursementDetailBYslipNO(String slipNo) {
		
		Session s=sessionFactory.openSession();
		//List dicalReimbursementDetail = null;
		try {
			  String sql ="SELECT E.REMARKS,E.MED_ST_DR_NAME, E.INS_DATE, E.INS_BY,E.GOVT_EXMPT_AMT,E.CASHMEMO_NO,E.CASHMEMO_DATE, E.SR_NO,SUM(E.AMOUNT)AMOUNT,SUM(E.PASSED_AMT)PASSED_AMT,E.SLIP_NO FROM MEDICLAIM_DETAILS E where E.SLIP_NO= :slipNo";
			  
			  SQLQuery query = s.createSQLQuery(sql); 
			  query.setParameter("slipNo", slipNo);
				
			  query.addEntity(MedicalReimbursementDetail.class);	
				
				
				
				return query.list();
			 
			
		} catch (Exception e) {
			
		}

		return null;
	}


}
