package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TrainingRequisition;
import com.hrms.repository.TrainingRequisitionDao;

@Service
public class TrainingRequisitionServiceImpl implements TrainingRequistionService{

	@Autowired SessionFactory sessionFactory;
	@Autowired TrainingRequisitionDao trainingRequisitionDao;
	
	@Override
	public void addTrainingRequisition(TrainingRequisition trainingRequistion) {
		
		trainingRequistion.setTrReqCode(trainingRequisitionDao.getMaxId("TRN"));
		trainingRequisitionDao.saveOrUpdate(trainingRequistion);
		
	}

	@Override
	public List<TrainingRequisition> getAllTrainingRequisition() {
		
		return this.trainingRequisitionDao.findAll();
	}

	@Override
	public void trainingRequisitionApproval(String trReqCode, String status) {
		Session session = sessionFactory.openSession();
		TrainingRequisition tr = session.find(TrainingRequisition.class, trReqCode);
		tr.setTrReqStatus(status);
		session.merge(tr);
		session.beginTransaction().commit();
		session.close();

		
		
	}

	@Override
	public TrainingRequisition findById(String trReqCode) {
		
		return this.trainingRequisitionDao.findById(trReqCode);
	}

	@Override
	public void updateTrainingRequisition(TrainingRequisition trainingRequisition) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TrainingRequisition tr = session.find(TrainingRequisition.class, trainingRequisition.getTrReqCode());
		tr.getListTransactionReqEmployeeDetail().clear();
		tr.getListTransactionRequisitionDetail().clear();
		tr.getListTransactionReqEmployeeDetail().addAll(trainingRequisition.getListTransactionReqEmployeeDetail());
		tr.getListTransactionRequisitionDetail().addAll(trainingRequisition.getListTransactionRequisitionDetail());
		
		session.merge(tr);
		session.getTransaction().commit();
		
	}

	@Override
	public List<TrainingRequisition> findTrainingRequisitionByStatusY() {
		
		try {
			Session session = sessionFactory.openSession();
			Query<TrainingRequisition> query = session.createQuery("from TrainingRequisition tr where "
					+ "tr.trReqStatus = :status", TrainingRequisition.class);
			query.setParameter("status", "Y");
			List<TrainingRequisition> result = query.getResultList();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
