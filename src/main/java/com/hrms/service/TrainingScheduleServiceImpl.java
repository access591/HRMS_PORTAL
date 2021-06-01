package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.model.TrainingSchedule;
import com.hrms.repository.TrainingScheduleDao;

@Service
public class TrainingScheduleServiceImpl implements TrainingScheduleService{

	@Autowired TrainingScheduleDao trainingScheduleDao; 
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void saveTrainingSchedule(TrainingSchedule trainingSchedule) {
		
		trainingSchedule.setTrScheduleCode(trainingScheduleDao.getMaxId("SCE"));
		
		trainingScheduleDao.saveOrUpdate(trainingSchedule);
		
	}
	@Override
	public List<TrainingSchedule> getAllTrainingSchedule() {
		
		return trainingScheduleDao.findAll();
	}
	@Override
	public TrainingSchedule findTrainingScheduleById(String trReqCode) {
		
		Session session = sessionFactory.openSession();
		Query<TrainingSchedule> query = session.createQuery("from TrainingSchedule e where e.trScheduleCode = :trReqCode", TrainingSchedule.class);
		query.setParameter("trReqCode", trReqCode);
		TrainingSchedule er = query.getSingleResult();
		return er;
		//return null;
	}
	
	@Override
	public void removeTrainingSchedule(String trReqCode) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object o = session.get(TrainingSchedule.class, trReqCode);
		TrainingSchedule e = (TrainingSchedule) o;
		
		session.delete(e);
		tx.commit();
		session.close();
		
		//Query q = session.createQuery("delete EmployeeRequisition e where e.reqCode = :reqCode");
		//q.setParameter("reqCode", reqCode);
		//q.executeUpdate();
	}
	
	@Override
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule) {
		
		System.out.println("TrainingSchedule : "+ trainingSchedule.getTrScheduleCode());
		Session session = sessionFactory.openSession();
		
			
			//session.beginTransaction();
		TrainingSchedule em = session.load(TrainingSchedule.class, trainingSchedule.getTrScheduleCode());
		Transaction tx = session.beginTransaction();	
		em = (TrainingSchedule) session.merge(trainingSchedule);
		tx.commit();
		
			
			
		
		
	}

}
