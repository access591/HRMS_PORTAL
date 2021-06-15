package com.hrms.service;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.ApplicantInfo;
import com.hrms.repository.ApplicantInfoDao;


@Service
public class ApplicantInfoServiceImpl implements ApplicantInfoService{

	@Autowired SessionFactory sessionFactory;
	@Autowired ApplicantInfoDao applicantInfoDao;
	
	@Override
	public void addApplicantInfo(ApplicantInfo applicantInfo) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		applicantInfo.setApplicantCode(this.applicantInfoDao.getMaxId("APP"));
		session.save(applicantInfo);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<ApplicantInfo> getAllApplicantInfo() {
		
		List<ApplicantInfo> list = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query<ApplicantInfo> query = session.createQuery("From ApplicantInfo",ApplicantInfo.class);
		
		list = query.getResultList();

		return list;
	}

	@Override
	public ApplicantInfo getApplicantInfoByApplicantCode(String applicantCode) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query<ApplicantInfo> query = session.createQuery("From ApplicantInfo a where a.applicantCode = :applicantCode",ApplicantInfo.class);
		query.setParameter("applicantCode", applicantCode);
		session.getTransaction().commit();
		return query.uniqueResult();
	}

	@Override
	public void updateApplicantInfoInterviewStatus(String applicantCode, String interviewStatus) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ApplicantInfo applicantInfo = session.find(ApplicantInfo.class, applicantCode);
			applicantInfo.setInterStatus(interviewStatus);
			session.merge(applicantInfo);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			System.out.println("error occur in update applicant interview status");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<ApplicantInfo> findApplicantInfoStatusHoldAndPending() {
		
		try {
			
			Session session = sessionFactory.openSession();
			Query<ApplicantInfo> query = session.createQuery("from ApplicantInfo a "
					+ "where a.interStatus = :status1"
					+ " or a.interStatus = :status2",ApplicantInfo.class);
			query.setParameter("status1", "Hold");
			query.setParameter("status2", "N");
			List<ApplicantInfo> result = query.getResultList();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	

}
