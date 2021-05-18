package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LocalConvyence;
@Repository
public class ConveyanceApprovalDaoImpl extends AbstractGenericDao<LocalConvyence> implements ConveyanceApprovalDao {
	@Autowired SessionFactory sessionFactory;
	@Override
	public List<LocalConvyence> getAllLocalConveyance() {
	//	String T="N";
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query<LocalConvyence> query = session.createQuery("from LocalConvyence e where e.approvalStatus ='n'",LocalConvyence.class);
			
			List<LocalConvyence> localConvyenceList = query.getResultList();
			session.getTransaction().commit();
			
			return localConvyenceList;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
