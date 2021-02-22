package com.hrms.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.City;
import com.hrms.model.Register;
import com.hrms.repository.RegisterDao;
@Service
public class RegisterServiceImpl implements RegisterService {
@Autowired
RegisterDao registerDao;
	@Override
	public void addRegister(Register res) {
		res.setIns_date(new Date());
		res.setRegCode(registerDao.getMAX_Id("REG"));
		this.registerDao.saveOrUpdate(res);
		
	}

	@Override
	public List<Register> getAllRegisters() {
		List<Register> listRegister = registerDao.findAll();
		return listRegister;
	}

	@Override
	public Register findRegisterById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRegister(Register res) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRegister(String id) {
		// TODO Auto-generated method stub
		
	}

}
