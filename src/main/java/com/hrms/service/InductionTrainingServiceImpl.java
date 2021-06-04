package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.InductionTraining;
import com.hrms.repository.InductionTrainingDao;
@Service
public class InductionTrainingServiceImpl implements InductionTrainingService {
@Autowired
InductionTrainingDao inductionTraining;

@Autowired
SessionFactory sessionfactory;
	@Override
	public void addInductionTraining(InductionTraining induct) {
		this.inductionTraining.saveOrUpdate(induct);
		
	}
	@Override
	public List<InductionTraining> getAllInductioTraining() {
		List<InductionTraining> listInduction=inductionTraining.findAll();
		return listInduction;
	}
	@Override
	public void removeInductionTr(Long id) {
		Session session = sessionfactory.openSession();
		Object o = session.get(InductionTraining.class, id);
		InductionTraining e = (InductionTraining) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		this.inductionTraining.delete(id);
		
	}

}
