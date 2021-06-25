package com.hrms.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.Award;
import com.hrms.model.Employee;
import com.hrms.repository.ArmsLicenseDao;

@Service
public class ArmsLicenseServiceImpl implements ArmsLicenseService {
	@Autowired
	ArmsLicenseDao armsLicenseDao;

	@Autowired
	SessionFactory sessionFactory;

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

		
		List<ArmsLicenseDetails> listArmsLicense = armsLicenseDao.findAll();
		return listArmsLicense;
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
		try {
			return armsLicenseDao.findArmsByEmpEmpCode(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArmsLicenseDetails> allArmsLicenseDetails() {

		Session session = sessionFactory.openSession();
		
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ArmsLicenseDetails> criteria = builder.createQuery(ArmsLicenseDetails.class);
		criteria.from(ArmsLicenseDetails.class);
		List<ArmsLicenseDetails> data = session.createQuery(criteria).getResultList();
		
		session.close();
		return data;
	}

}
