package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.model.InterviewMaster;
import com.hrms.repository.InterviewMasterDao;

@Service
public class InterviewMasterServiceImpl implements InterviewMasterService{

	@Autowired SessionFactory sessionFactory;
	@Autowired InterviewMasterDao interviewMasterDao;
	
	@Override
	public void addInterviewMaster(InterviewMaster interviewMaster) {
		Session session = sessionFactory.openSession();
		interviewMaster.setInterviewCode(interviewMasterDao.getMaxId("INT"));
		session.beginTransaction();
		session.save(interviewMaster);
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public List<InterviewMaster> getAllInterviewMaster() {
		
		return interviewMasterDao.findAll();
	}

	@Override
	public void interviewFinalapproval(String applicantCode, String interviewCode, String finalApprovalStatus) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		InterviewMaster im = session.find(InterviewMaster.class, interviewCode);

		
		im.setSelectionStatus(finalApprovalStatus);
		
		session.merge(im);
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public List<InterviewMaster> getFinalSelection() {
		
		try {
			Session session = sessionFactory.openSession();
			Query<InterviewMaster> query = session.createQuery("from InterviewMaster i where i.selectionStatus"
					+ "=:status", InterviewMaster.class);
			query.setParameter("status", "Selected");
			List<InterviewMaster> result = query.getResultList();
			session.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InterviewMaster findinterviewMasterById(String interviewCode) {
		try {
			Session session = sessionFactory.openSession();
			Query<InterviewMaster> query = session.createQuery("from InterviewMaster e where "
					+ "e.interviewCode = :interviewCode", 
					InterviewMaster.class);
			query.setParameter("interviewCode", interviewCode);
			InterviewMaster er = query.getSingleResult();
			return er;
		}catch(Exception e) {
			System.out.println("========error block");
			e.printStackTrace();
		}
		return null;
	}

	
}
