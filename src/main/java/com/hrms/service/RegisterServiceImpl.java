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
		res.setInsDate(new Date());
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

		return registerDao.findById(id);
	}

	@Override
	public void updateRegister(Register res) {
		res.setDescriptionReg(res.getDescriptionReg());
		res.setUpdDate(res.getUpdDate());
		res.setActive(res.getActive());
		this.registerDao.saveOrUpdate(res);

	}

	@Override
	public void removeRegister(String id) {
		this.registerDao.delete(id);

	}

}
