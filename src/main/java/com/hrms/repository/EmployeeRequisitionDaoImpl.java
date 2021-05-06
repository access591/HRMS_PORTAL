package com.hrms.repository;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.EmployeeRequisition;

@Repository
public class EmployeeRequisitionDaoImpl extends AbstractGenericDao<EmployeeRequisition> implements EmployeeRequisitionDao{

	@Autowired SessionFactory sessionFactory;
	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusN() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from EmployeeRequisition e where e.status = 'N'");
		List<EmployeeRequisition> listEmployeeReq = query.list();
		return listEmployeeReq;
	}

}
