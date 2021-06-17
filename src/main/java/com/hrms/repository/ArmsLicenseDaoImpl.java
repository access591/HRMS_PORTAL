package com.hrms.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.ArmsLicenseDetails;

@Repository
public class ArmsLicenseDaoImpl  extends AbstractGenericDao<ArmsLicenseDetails> implements ArmsLicenseDao {
	@Autowired SessionFactory sessionFactory;
	@Override
	public  ArmsLicenseDetails findArmsByEmpEmpCode(String empCode) {
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query<ArmsLicenseDetails> query = session.createQuery("from ArmsLicenseDetails e where e.empCode=:empCode",ArmsLicenseDetails.class);
			query.setParameter("empCode",empCode);
			
			return query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

