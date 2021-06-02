package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.TrainingRegister;
import com.hrms.repository.TrainingRegisterDao;

@Service
public class TrainingRegisterServiceImpl implements TrainingRegisterService {
	@Autowired
	TrainingRegisterDao trainingRegisterDao;

	@Override
	public void addTrainingRegister(TrainingRegister trainingRegister) {
		trainingRegister.setTrRegCode(trainingRegisterDao.getMaxId("TRC"));
		this.trainingRegisterDao.saveOrUpdate(trainingRegister);
	}

	@Override
	public List<TrainingRegister> getAllTrainingRegisters() {
		List<TrainingRegister> listTrainingRegister = trainingRegisterDao.findAll();
		return listTrainingRegister;
	}

	@Override
	public TrainingRegister findTrainingRegisterById(String id) {
		return trainingRegisterDao.findById(id);
	}

	@Override
	public void updateTrainingRegister(TrainingRegister t) {
		this.trainingRegisterDao.saveOrUpdate(t);

	}

	@Override
	public void removeTrainingRegister(String id) {
		this.trainingRegisterDao.delete(id);

	}

}
