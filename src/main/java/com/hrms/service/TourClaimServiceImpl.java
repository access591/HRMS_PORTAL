package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TourClaim;
import com.hrms.repository.TourClaimDao;

@Service
public class TourClaimServiceImpl implements TourClaimService {
	@Autowired
	TourClaimDao tourClaimDao;
	@Autowired SessionFactory sessionFactory;
	

	@Override
	public void AddTourClaim(TourClaim tourClaim) {
		tourClaim.setTourClaimId(tourClaimDao.getMaxId("TCI"));
		this.tourClaimDao.saveOrUpdate(tourClaim);

	}

	@Override
	public List<TourClaim> getAllTourClaimBetweenDate(Date fromDate,Date toDate) {

		try {
			
			Session session = sessionFactory.openSession();
			Query<TourClaim> query = session.createQuery("from TourClaim tr where tr.tourClaimDate>=:fromDate and "
					+ "tr.tourClaimDate<=:toDate", TourClaim.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			
			List<TourClaim> result = query.getResultList();
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<TourClaim> findTourClaimByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<TourClaim> query = session.createQuery("from TourClaim t inner join fetch t.empCode e"
					+ " where e.empCode=:empCode and t.tourClaimDate>=:fromDate and "
					+ "t.tourClaimDate<=:toDate",TourClaim.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<TourClaim> tourClaimList = query.getResultList();
			return tourClaimList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
