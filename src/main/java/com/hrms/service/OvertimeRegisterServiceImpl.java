package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.OvertimeRegister;

@Service
public class OvertimeRegisterServiceImpl implements OvertimeRegisterService{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public List<OvertimeRegister> findOverTimeRegisterByEmpCodeBetweenDate(String empCode, Date fromDate, Date toDate) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<OvertimeRegister> query = session.createQuery("from OvertimeRegister o "
					+ "inner join fetch o.employee e where e.empCode = :empCode and "
					+ "o.timeIN = :fromDate and o.timeOut = :toDate", OvertimeRegister.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<OvertimeRegister> result = query.getResultList();
			System.out.println("esult lenth : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
