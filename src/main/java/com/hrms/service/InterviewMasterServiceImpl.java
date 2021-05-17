package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public InterviewMaster getApplicantInfoByApplicantCode() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		return null;
	}

}
