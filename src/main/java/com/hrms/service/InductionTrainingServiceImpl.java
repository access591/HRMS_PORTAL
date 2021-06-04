package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.InductionTraining;
import com.hrms.repository.InductionTrainingDao;
@Service
public class InductionTrainingServiceImpl implements InductionTrainingService {
@Autowired
InductionTrainingDao inductionTraining;
	@Override
	public void addInductionTraining(InductionTraining induct) {
		this.inductionTraining.saveOrUpdate(induct);
		
	}

}
