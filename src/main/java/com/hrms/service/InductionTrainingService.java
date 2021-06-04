package com.hrms.service;

import java.util.List;

import com.hrms.model.InductionTraining;

public interface InductionTrainingService {

	void addInductionTraining(InductionTraining induct);

	List<InductionTraining> getAllInductioTraining();

}
