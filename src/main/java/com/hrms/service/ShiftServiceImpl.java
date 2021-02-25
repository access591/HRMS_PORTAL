package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.Shift;
import com.hrms.repository.ShiftDao;
@Service
public class ShiftServiceImpl implements ShiftService {
@Autowired
ShiftDao ShiftDao;
	@Override
	public void addShift(Shift shift) {
		shift.setShiftCode(ShiftDao.getMAX_Id("SFC"));
		this.ShiftDao.saveOrUpdate(shift);
		
	}

	@Override
	public List<Shift> getAllShifts() {
	
		List<Shift> listShift = ShiftDao.findAll();
		return listShift;
	}

	@Override
	public Shift findShiftById(String id) {
	
		return ShiftDao.findById(id);
	}

	@Override
	public void updateShift(Shift s) {
		this.ShiftDao.saveOrUpdate(s);
		
	}

	@Override
	public void removeShift(String id) {
		this.ShiftDao.delete(id);

		
	}

}
