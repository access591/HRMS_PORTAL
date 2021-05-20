package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LtaRequest;
@Repository
public class LtaApprovalDaoImpl extends AbstractGenericDao<LtaRequest> implements LtaApprovalDao{
	@Autowired SessionFactory sessionFactory;



	@Override
	public List<LtaRequest> getAllLtaApproval() {
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LtaRequest> query = session.createQuery("from LtaRequest  e where e.approvalStatus ='N'",LtaRequest.class);
			
			List<LtaRequest> localConvyenceList = query.getResultList();
			session.getTransaction().commit();
			
			return localConvyenceList;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;

}
}

