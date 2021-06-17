package com.hrms.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.BudgetProvision;
import com.hrms.repository.ArmsLicenseDao;
@Service
public class ArmsLicenseServiceImpl implements ArmsLicenseService {
	@Autowired
	ArmsLicenseDao armsLicenseDao;
	
	@Autowired SessionFactory sessionFactory;
	
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
		
		return armsLicenseDao.findAll();
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
		return armsLicenseDao.findArmsByEmpEmpCode(id);
	}
	@Override
	public List<ArmsLicenseDetails> armsLicenseDetailsList() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try  {

			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ArmsLicenseDetails> criteria = builder.createQuery(ArmsLicenseDetails.class);
			criteria.from(ArmsLicenseDetails.class);
			
			tx.commit();
			return session.createQuery(criteria).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

}
