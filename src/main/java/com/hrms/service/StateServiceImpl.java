package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.State;
import com.hrms.repository.StateDao;
@Service
public class StateServiceImpl  implements StateService{

	@Autowired
	StateDao StateDao;
	@Override
	public void addState(State state) {
	state.setStateCode(StateDao.getMAX_Id("ST"));	
	this.StateDao.saveOrUpdate(state);
		
	}

	@Override
	public List<State> getAllStates() {
	List<State> listState = StateDao.findAll();
		return listState;
	}

	@Override
	public State findStateById(String id) {
	
		return StateDao.findById(id);
	}

	@Override
	public void updateState(State s) {
	
		this.StateDao.saveOrUpdate(s);	
	}

	@Override
	public void removeState(String id) {
		
		this.StateDao.delete(id);
	}

}
